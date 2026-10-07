package com.example.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.data.model.SubjectStat
import com.example.data.model.UserProfile
import org.json.JSONObject

class PreferencesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("pipo_quiz_prefs", Context.MODE_PRIVATE)

    fun loadProfile(): UserProfile {
        val age = prefs.getInt("pref_age", 7)
        val totalXp = prefs.getInt("pref_total_xp", 0)
        val streak = prefs.getInt("pref_streak", 0)
        val bestStreak = prefs.getInt("pref_best_streak", 0)
        val totalAnswered = prefs.getInt("pref_total_answered", 0)
        val totalCorrect = prefs.getInt("pref_total_correct", 0)
        val quizzesCompleted = prefs.getInt("pref_quizzes_completed", 0)
        val soundEnabled = prefs.getBoolean("pref_sound_enabled", true)

        val unlockedAchievements = prefs.getStringSet("pref_achievements", emptySet()) ?: emptySet()
        val subjectsPlayed = prefs.getStringSet("pref_subjects_played", emptySet()) ?: emptySet()
        val weaknesses = prefs.getStringSet("pref_weaknesses", emptySet())?.toList() ?: emptyList()

        val statsJson = prefs.getString("pref_subject_stats", null)
        val subjectStats = mutableMapOf<String, SubjectStat>()
        if (!statsJson.isNullOrEmpty()) {
            try {
                val json = JSONObject(statsJson)
                val keys = json.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    val obj = json.getJSONObject(key)
                    val answered = obj.optInt("answered", 0)
                    val correct = obj.optInt("correct", 0)
                    subjectStats[key] = SubjectStat(answered, correct)
                }
            } catch (_: Exception) {}
        }

        return UserProfile(
            age = age,
            totalXp = totalXp,
            streak = streak,
            bestStreak = bestStreak,
            totalAnswered = totalAnswered,
            totalCorrect = totalCorrect,
            quizzesCompleted = quizzesCompleted,
            soundEnabled = soundEnabled,
            subjectStats = subjectStats,
            unlockedAchievements = unlockedAchievements,
            subjectsPlayed = subjectsPlayed,
            weaknesses = weaknesses
        )
    }

    fun saveAge(age: Int) {
        prefs.edit().putInt("pref_age", age).apply()
    }

    fun setSoundEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("pref_sound_enabled", enabled).apply()
    }

    fun recordQuizCompletion(
        earnedXp: Int,
        quizAnswered: Int,
        quizCorrect: Int,
        maxStreakInQuiz: Int,
        subjectId: String,
        newUnlockedAchievements: Set<String>,
        newWeaknesses: List<String>
    ): UserProfile {
        val current = loadProfile()

        val updatedXp = current.totalXp + earnedXp
        val updatedAnswered = current.totalAnswered + quizAnswered
        val updatedCorrect = current.totalCorrect + quizCorrect
        val updatedQuizzes = current.quizzesCompleted + 1
        val updatedBestStreak = maxOf(current.bestStreak, maxStreakInQuiz)

        val updatedSubjectsPlayed = current.subjectsPlayed.toMutableSet().apply { add(subjectId) }
        val updatedAchievements = current.unlockedAchievements.toMutableSet().apply {
            addAll(newUnlockedAchievements)
        }

        val updatedStats = current.subjectStats.toMutableMap()
        val prevStat = updatedStats[subjectId] ?: SubjectStat()
        updatedStats[subjectId] = SubjectStat(
            answered = prevStat.answered + quizAnswered,
            correct = prevStat.correct + quizCorrect
        )

        val updatedWeaknesses = (current.weaknesses + newWeaknesses).distinct().takeLast(5)

        val statsJson = JSONObject()
        updatedStats.forEach { (k, v) ->
            val obj = JSONObject().apply {
                put("answered", v.answered)
                put("correct", v.correct)
            }
            statsJson.put(k, obj)
        }

        prefs.edit()
            .putInt("pref_total_xp", updatedXp)
            .putInt("pref_total_answered", updatedAnswered)
            .putInt("pref_total_correct", updatedCorrect)
            .putInt("pref_quizzes_completed", updatedQuizzes)
            .putInt("pref_best_streak", updatedBestStreak)
            .putStringSet("pref_subjects_played", updatedSubjectsPlayed)
            .putStringSet("pref_achievements", updatedAchievements)
            .putStringSet("pref_weaknesses", updatedWeaknesses.toSet())
            .putString("pref_subject_stats", statsJson.toString())
            .apply()

        return current.copy(
            totalXp = updatedXp,
            totalAnswered = updatedAnswered,
            totalCorrect = updatedCorrect,
            quizzesCompleted = updatedQuizzes,
            bestStreak = updatedBestStreak,
            subjectsPlayed = updatedSubjectsPlayed,
            unlockedAchievements = updatedAchievements,
            weaknesses = updatedWeaknesses,
            subjectStats = updatedStats
        )
    }

    fun resetProgress() {
        prefs.edit().clear().apply()
    }
}
