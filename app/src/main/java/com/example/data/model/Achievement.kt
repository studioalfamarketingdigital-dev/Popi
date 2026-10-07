package com.example.data.model

enum class AchievementCategory(val title: String, val icon: String) {
    ALL("Todas", "🌟"),
    LEARNING("Aprendizado", "📚"),
    ENGAGEMENT("Engajamento", "🔥"),
    CHALLENGE("Desafios", "🎯")
}

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val category: AchievementCategory,
    val maxProgress: Int = 1,
    val isUnlocked: Boolean = false,
    val unlockedAt: Long? = null
)

object DefaultAchievements {
    fun getAll(): List<Achievement> = listOf(
        // Marcos de Aprendizado
        Achievement(
            id = "math_master",
            title = "Dominou a Tabuada",
            description = "Acertou 15 perguntas de Matemática com maestria!",
            icon = "🔢",
            category = AchievementCategory.LEARNING,
            maxProgress = 15
        ),
        Achievement(
            id = "speed_reader",
            title = "Leitor Veloz",
            description = "Completou 3 quizzes inteiros de Português!",
            icon = "📖",
            category = AchievementCategory.LEARNING,
            maxProgress = 3
        ),
        Achievement(
            id = "little_scientist",
            title = "Pequeno Cientista",
            description = "Acertou 10 perguntas sobre natureza e ciências!",
            icon = "🔬",
            category = AchievementCategory.LEARNING,
            maxProgress = 10
        ),
        Achievement(
            id = "planet_explorer",
            title = "Explorador do Planeta",
            description = "Descobriu o mundo respondendo 10 perguntas de Geografia!",
            icon = "🌎",
            category = AchievementCategory.LEARNING,
            maxProgress = 10
        ),
        Achievement(
            id = "time_traveler",
            title = "Viajante do Tempo",
            description = "Viajou pela história com 10 acertos históricos!",
            icon = "🏛️",
            category = AchievementCategory.LEARNING,
            maxProgress = 10
        ),
        Achievement(
            id = "curious_mind",
            title = "Mente Curiosa",
            description = "Jogou desafios de todas as 6 matérias!",
            icon = "🧠",
            category = AchievementCategory.LEARNING,
            maxProgress = 6
        ),

        // Engajamento & Sequência
        Achievement(
            id = "streak_three",
            title = "Três Dias Seguidos",
            description = "Construiu uma sequência de pelo menos 3 acertos seguidos!",
            icon = "⚡",
            category = AchievementCategory.ENGAGEMENT,
            maxProgress = 3
        ),
        Achievement(
            id = "on_fire",
            title = "Em Chamas",
            description = "Conquistou uma sequência de 5 acertos seguidos no quiz!",
            icon = "🔥",
            category = AchievementCategory.ENGAGEMENT,
            maxProgress = 5
        ),
        Achievement(
            id = "study_week",
            title = "Semana de Estudos",
            description = "Mostrou grande dedicação completando 7 quizzes educativos!",
            icon = "📅",
            category = AchievementCategory.ENGAGEMENT,
            maxProgress = 7
        ),
        Achievement(
            id = "hundred_questions",
            title = "100 Perguntas Respondidas",
            description = "Um marco épico: respondeu 100 perguntas no PIPO!",
            icon = "💯",
            category = AchievementCategory.ENGAGEMENT,
            maxProgress = 100
        ),

        // Desafios Específicos & Evolução
        Achievement(
            id = "first_quiz",
            title = "Primeiro Quiz",
            description = "Concluiu seu primeiro desafio no PIPO!",
            icon = "🏆",
            category = AchievementCategory.CHALLENGE,
            maxProgress = 1
        ),
        Achievement(
            id = "perfect_score",
            title = "Quiz Perfeito",
            description = "Acertou todas as 10 perguntas em uma única rodada!",
            icon = "🎯",
            category = AchievementCategory.CHALLENGE,
            maxProgress = 1
        ),
        Achievement(
            id = "leveling_up",
            title = "Subindo de Nível",
            description = "Evoluiu para o Nível 2 e virou um Descobridor!",
            icon = "🚀",
            category = AchievementCategory.CHALLENGE,
            maxProgress = 1
        ),
        Achievement(
            id = "master_pipo",
            title = "Mestre Pipo",
            description = "Alcançou o Nível 4 (Mestre) ou superior!",
            icon = "👑",
            category = AchievementCategory.CHALLENGE,
            maxProgress = 1
        ),
        Achievement(
            id = "ai_friend",
            title = "Amigo da IA",
            description = "Respondeu um desafio gerado pela inteligência artificial!",
            icon = "🤖",
            category = AchievementCategory.CHALLENGE,
            maxProgress = 1
        ),
        Achievement(
            id = "pipo_champion",
            title = "Campeão Pipo",
            description = "Acumulou mais de 250 pontos de XP no total!",
            icon = "⭐",
            category = AchievementCategory.CHALLENGE,
            maxProgress = 250
        )
    )
}
