package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.PreferencesManager
import com.example.data.model.Achievement
import com.example.data.model.DefaultAchievements
import com.example.data.model.MascotState
import com.example.data.model.Question
import com.example.data.model.Subject
import com.example.data.model.UserProfile
import com.example.data.remote.GeminiQuestionRepository
import com.example.data.repository.QuestionBank
import com.example.ui.sound.SoundManager
import com.example.ui.sound.TtsManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class AppScreen {
    HOME,
    AGE_SELECTION,
    SUBJECT_SELECTION,
    QUIZ,
    QUIZ_RESULT,
    PROGRESS,
    ACHIEVEMENTS
}

data class QuizUiState(
    val currentScreen: AppScreen = AppScreen.HOME,
    val profile: UserProfile = UserProfile(),
    val currentSubject: Subject = Subject.MATEMATICA,
    val selectedAge: Int = 7,

    // Current quiz session
    val questions: List<Question> = emptyList(),
    val questionIndex: Int = 0,
    val selectedOption: String? = null,
    val isCorrect: Boolean? = null,
    val isAnswerLocked: Boolean = false,
    val showExplanation: Boolean = false,
    val explanationText: String = "",
    val correctQuestionsCount: Int = 0,
    val sessionEarnedXp: Int = 0,
    val currentStreak: Int = 0,
    val maxStreakInSession: Int = 0,
    val sessionWeaknesses: List<String> = emptyList(),

    // Mascot & Animation
    val mascotState: MascotState = MascotState.IDLE,
    val mascotQuote: String = "Olá! Vamos aprender brincando?",
    val triggerConfetti: Boolean = false,
    val isLoadingAi: Boolean = false,
    val newlyUnlockedAchievements: List<Achievement> = emptyList(),
    val didLevelUp: Boolean = false
)

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = PreferencesManager(application)
    private val geminiRepo = GeminiQuestionRepository()
    val soundManager = SoundManager()
    val ttsManager = TtsManager(application)

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        val loadedProfile = prefs.loadProfile()
        soundManager.isEnabled = loadedProfile.soundEnabled
        _uiState.update {
            it.copy(
                profile = loadedProfile,
                selectedAge = loadedProfile.age
            )
        }
    }

    fun navigateTo(screen: AppScreen) {
        soundManager.playClick()
        ttsManager.stop()
        _uiState.update {
            it.copy(
                currentScreen = screen,
                mascotState = when (screen) {
                    AppScreen.HOME -> MascotState.IDLE
                    AppScreen.AGE_SELECTION -> MascotState.CURIOUS
                    AppScreen.SUBJECT_SELECTION -> MascotState.HAPPY
                    AppScreen.PROGRESS -> MascotState.THINKING
                    AppScreen.ACHIEVEMENTS -> MascotState.CELEBRATING
                    else -> it.mascotState
                },
                mascotQuote = when (screen) {
                    AppScreen.HOME -> "Pronto para um novo desafio?"
                    AppScreen.AGE_SELECTION -> "Quantos aninhos você tem?"
                    AppScreen.SUBJECT_SELECTION -> "Qual matéria vamos explorar hoje?"
                    AppScreen.PROGRESS -> "Veja só como você está evoluindo!"
                    AppScreen.ACHIEVEMENTS -> "Você conquistou medalhas incríveis!"
                    else -> it.mascotQuote
                }
            )
        }
    }

    fun selectAge(age: Int) {
        soundManager.playClick()
        prefs.saveAge(age)
        _uiState.update {
            it.copy(
                selectedAge = age,
                profile = it.profile.copy(age = age),
                currentScreen = AppScreen.SUBJECT_SELECTION,
                mascotState = MascotState.HAPPY,
                mascotQuote = "Que legal! Vamos escolher uma matéria divertida!"
            )
        }
    }

    fun toggleSound() {
        val newState = !soundManager.isEnabled
        soundManager.isEnabled = newState
        prefs.setSoundEnabled(newState)
        _uiState.update {
            it.copy(profile = it.profile.copy(soundEnabled = newState))
        }
    }

    fun readQuestionAloud() {
        val currentQ = currentQuestion ?: return
        val textToSpeak = buildString {
            append(currentQ.question)
            append(". ")
            currentQ.options.forEachIndexed { i, opt ->
                val letter = ('A' + i).toString()
                append("Opção $letter: $opt. ")
            }
        }
        ttsManager.speak(textToSpeak)
    }

    fun startQuiz(subject: Subject) {
        soundManager.playClick()
        val age = _uiState.value.selectedAge
        val recentWeakness = _uiState.value.profile.weaknesses.lastOrNull()

        // Prepare initial list of 10 questions from local bank
        val initialQuestions = QuestionBank.getQuestionsFor(
            subject = subject,
            age = age,
            count = 10,
            reinforceTopic = recentWeakness
        )

        _uiState.update {
            it.copy(
                currentScreen = AppScreen.QUIZ,
                currentSubject = subject,
                questions = initialQuestions,
                questionIndex = 0,
                selectedOption = null,
                isCorrect = null,
                isAnswerLocked = false,
                showExplanation = false,
                explanationText = "",
                correctQuestionsCount = 0,
                sessionEarnedXp = 0,
                currentStreak = 0,
                maxStreakInSession = 0,
                sessionWeaknesses = emptyList(),
                triggerConfetti = false,
                newlyUnlockedAchievements = emptyList(),
                mascotState = MascotState.IDLE,
                mascotQuote = "Vamos lá! Você consegue!"
            )
        }

        // Try prefetching/enriching an AI question in the background via Gemini
        tryEnrichWithAiQuestion(subject, age, recentWeakness)
    }

    private fun tryEnrichWithAiQuestion(subject: Subject, age: Int, topicHint: String?) {
        viewModelScope.launch {
            try {
                val aiQuestion = geminiRepo.generateEducationalQuestion(subject, age, topicHint)
                if (aiQuestion != null) {
                    _uiState.update { state ->
                        if (state.questions.isNotEmpty()) {
                            val mutable = state.questions.toMutableList()
                            // Replace question #5 with the AI question
                            if (mutable.size >= 5) {
                                mutable[4] = aiQuestion
                            } else {
                                mutable.add(aiQuestion)
                            }
                            state.copy(questions = mutable)
                        } else state
                    }
                }
            } catch (_: Exception) {}
        }
    }

    val currentQuestion: Question?
        get() = _uiState.value.questions.getOrNull(_uiState.value.questionIndex)

    fun onOptionSelected(option: String) {
        val state = _uiState.value
        if (state.isAnswerLocked) return // double tap prevention
        val question = currentQuestion ?: return

        ttsManager.stop()
        val isAnswerCorrect = option.trim() == question.correctAnswer.trim()

        if (isAnswerCorrect) {
            // Correct answer
            soundManager.playCorrect()
            val newStreak = state.currentStreak + 1
            val maxStreak = maxOf(state.maxStreakInSession, newStreak)

            // Calculate XP bonuses
            var xpGain = 10
            if (newStreak == 3) {
                xpGain += 5
                soundManager.playStreakBonus()
            } else if (newStreak == 5) {
                xpGain += 10
                soundManager.playStreakBonus()
            } else if (newStreak >= 10) {
                xpGain += 25
                soundManager.playStreakBonus()
            }

            val celebratoryQuotes = listOf(
                "Boa!",
                "Mandou muito bem!",
                "Você conseguiu!",
                "Excelente!",
                "Uau! Sensacional!"
            )
            val quote = celebratoryQuotes.random()

            _uiState.update {
                it.copy(
                    selectedOption = option,
                    isCorrect = true,
                    isAnswerLocked = true,
                    correctQuestionsCount = it.correctQuestionsCount + 1,
                    sessionEarnedXp = it.sessionEarnedXp + xpGain,
                    currentStreak = newStreak,
                    maxStreakInSession = maxStreak,
                    mascotState = MascotState.HAPPY,
                    mascotQuote = "$quote +$xpGain XP",
                    triggerConfetti = true
                )
            }

            // Automatically advance after ~1.2s per specification
            viewModelScope.launch {
                delay(1200)
                advanceToNextQuestionOrFinish()
            }
        } else {
            // Incorrect answer
            soundManager.playWrong()
            val encouragingQuotes = listOf(
                "Quase! Vamos pensar juntos.",
                "Tente mais uma vez, você está aprendendo!",
                "Não desanime! O erro ajuda a gente a crescer.",
                "Vamos ver por que isso aconteceu!"
            )
            val quote = encouragingQuotes.random()

            val newWeaknessTopic = question.subject.title

            _uiState.update {
                it.copy(
                    selectedOption = option,
                    isCorrect = false,
                    isAnswerLocked = true,
                    currentStreak = 0, // reset streak
                    showExplanation = true,
                    explanationText = question.explanation,
                    mascotState = MascotState.ENCOURAGING,
                    mascotQuote = quote,
                    triggerConfetti = false,
                    sessionWeaknesses = (it.sessionWeaknesses + newWeaknessTopic).distinct()
                )
            }
        }
    }

    fun dismissExplanationAndContinue() {
        soundManager.playClick()
        advanceToNextQuestionOrFinish()
    }

    private fun advanceToNextQuestionOrFinish() {
        val state = _uiState.value
        val nextIndex = state.questionIndex + 1

        if (nextIndex < state.questions.size) {
            _uiState.update {
                it.copy(
                    questionIndex = nextIndex,
                    selectedOption = null,
                    isCorrect = null,
                    isAnswerLocked = false,
                    showExplanation = false,
                    explanationText = "",
                    triggerConfetti = false,
                    mascotState = MascotState.IDLE,
                    mascotQuote = "Pronto para a próxima pergunta?"
                )
            }
        } else {
            // Quiz completed!
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        val state = _uiState.value
        soundManager.playCelebration()

        val unlockedNow = mutableListOf<Achievement>()
        val allAchievements = DefaultAchievements.getAll()
        val alreadyUnlocked = state.profile.unlockedAchievements

        fun tryUnlock(id: String) {
            if (!alreadyUnlocked.contains(id)) {
                allAchievements.find { it.id == id }?.let { unlockedNow.add(it) }
            }
        }

        val projectedXp = state.profile.totalXp + state.sessionEarnedXp
        val totalAnsweredProjected = state.profile.totalAnswered + state.questions.size
        val quizzesDoneProjected = state.profile.quizzesCompleted + 1
        val maxStreak = maxOf(state.profile.bestStreak, state.maxStreakInSession)

        // 1. Primeiro Quiz
        tryUnlock("first_quiz")

        // 2. Quiz Perfeito (10 de 10)
        if (state.correctQuestionsCount == state.questions.size && state.questions.isNotEmpty()) {
            tryUnlock("perfect_score")
        }

        // 3. Sequências
        if (maxStreak >= 3) {
            tryUnlock("streak_three")
        }
        if (maxStreak >= 5) {
            tryUnlock("on_fire")
        }

        // 4. Semana de Estudos (7 quizzes)
        if (quizzesDoneProjected >= 7) {
            tryUnlock("study_week")
        }

        // 5. 100 Perguntas Respondidas
        if (totalAnsweredProjected >= 100) {
            tryUnlock("hundred_questions")
        }

        // 6. Subindo de Nível (Nível 2+) & Mestre Pipo (Nível 4+)
        if (projectedXp >= 50) {
            tryUnlock("leveling_up")
        }
        if (projectedXp >= 300) {
            tryUnlock("master_pipo")
        }

        // 7. Campeão Pipo (250+ XP)
        if (projectedXp >= 250) {
            tryUnlock("pipo_champion")
        }

        // 8. Amigo da IA
        if (state.questions.any { it.isAiGenerated }) {
            tryUnlock("ai_friend")
        }

        // Temporary calculation of subject stats for this round
        val currentSubjId = state.currentSubject.id
        val prevSubjCorrect = state.profile.subjectStats[currentSubjId]?.correct ?: 0
        val newSubjCorrect = prevSubjCorrect + state.correctQuestionsCount

        if (currentSubjId == Subject.MATEMATICA.id && newSubjCorrect >= 15) {
            tryUnlock("math_master")
        }
        if (currentSubjId == Subject.PORTUGUES.id && ((state.profile.subjectStats[Subject.PORTUGUES.id]?.answered ?: 0) + state.questions.size) / 10 >= 3) {
            tryUnlock("speed_reader")
        }
        if (currentSubjId == Subject.CIENCIAS.id && newSubjCorrect >= 10) {
            tryUnlock("little_scientist")
        }
        if (currentSubjId == Subject.GEOGRAFIA.id && newSubjCorrect >= 10) {
            tryUnlock("planet_explorer")
        }
        if (currentSubjId == Subject.HISTORIA.id && newSubjCorrect >= 10) {
            tryUnlock("time_traveler")
        }

        // Save progress to persistent storage
        val updatedProfile = prefs.recordQuizCompletion(
            earnedXp = state.sessionEarnedXp,
            quizAnswered = state.questions.size,
            quizCorrect = state.correctQuestionsCount,
            maxStreakInQuiz = state.maxStreakInSession,
            subjectId = state.currentSubject.id,
            newUnlockedAchievements = unlockedNow.map { it.id }.toSet(),
            newWeaknesses = state.sessionWeaknesses
        )

        // Mente Curiosa (todas as 6 matérias)
        if (updatedProfile.subjectsPlayed.size >= 6) {
            if (!updatedProfile.unlockedAchievements.contains("curious_mind")) {
                allAchievements.find { it.id == "curious_mind" }?.let { unlockedNow.add(it) }
            }
        }

        val didLevelUp = updatedProfile.levelInfo.levelNumber > state.profile.levelInfo.levelNumber

        _uiState.update {
            it.copy(
                currentScreen = AppScreen.QUIZ_RESULT,
                profile = updatedProfile,
                newlyUnlockedAchievements = unlockedNow,
                didLevelUp = didLevelUp,
                mascotState = if (didLevelUp) MascotState.CELEBRATING else MascotState.VICTORY,
                mascotQuote = if (didLevelUp) {
                    "SUBIU DE NÍVEL! Agora você é ${updatedProfile.levelInfo.title} ${updatedProfile.levelInfo.icon}!"
                } else {
                    "QUIZ CONCLUÍDO! Você foi sensacional!"
                },
                triggerConfetti = true
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        soundManager.release()
        ttsManager.release()
    }
}
