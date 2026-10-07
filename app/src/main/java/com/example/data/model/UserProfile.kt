package com.example.data.model

data class LevelInfo(
    val levelNumber: Int,
    val title: String,
    val icon: String,
    val minXp: Int,
    val maxXp: Int
)

data class SubjectStat(
    val answered: Int = 0,
    val correct: Int = 0
) {
    val accuracy: Int
        get() = if (answered > 0) ((correct.toFloat() / answered) * 100).toInt() else 0
}

data class UserProfile(
    val age: Int = 7,
    val totalXp: Int = 0,
    val streak: Int = 0,
    val bestStreak: Int = 0,
    val totalAnswered: Int = 0,
    val totalCorrect: Int = 0,
    val quizzesCompleted: Int = 0,
    val soundEnabled: Boolean = true,
    val subjectStats: Map<String, SubjectStat> = emptyMap(),
    val unlockedAchievements: Set<String> = emptySet(),
    val subjectsPlayed: Set<String> = emptySet(),
    val weaknesses: List<String> = emptyList() // Adaptive hints: e.g. "multiplicação", "subtração"
) {
    val levelInfo: LevelInfo
        get() = calculateLevel(totalXp)

    val accuracyRate: Int
        get() = if (totalAnswered > 0) ((totalCorrect.toFloat() / totalAnswered) * 100).toInt() else 0

    val progressInLevel: Float
        get() {
            val range = (levelInfo.maxXp - levelInfo.minXp).coerceAtLeast(1)
            val current = (totalXp - levelInfo.minXp).coerceAtLeast(0)
            return (current.toFloat() / range).coerceIn(0f, 1f)
        }

    val xpToNextLevel: Int
        get() = (levelInfo.maxXp - totalXp).coerceAtLeast(0)

    fun getAchievementProgress(achievementId: String): Int {
        if (unlockedAchievements.contains(achievementId)) {
            val max = DefaultAchievements.getAll().find { it.id == achievementId }?.maxProgress ?: 1
            return max
        }
        return when (achievementId) {
            "math_master" -> (subjectStats[Subject.MATEMATICA.id]?.correct ?: 0).coerceAtMost(15)
            "speed_reader" -> ((subjectStats[Subject.PORTUGUES.id]?.answered ?: 0) / 10).coerceAtMost(3)
            "little_scientist" -> (subjectStats[Subject.CIENCIAS.id]?.correct ?: 0).coerceAtMost(10)
            "planet_explorer" -> (subjectStats[Subject.GEOGRAFIA.id]?.correct ?: 0).coerceAtMost(10)
            "time_traveler" -> (subjectStats[Subject.HISTORIA.id]?.correct ?: 0).coerceAtMost(10)
            "curious_mind" -> subjectsPlayed.size.coerceAtMost(6)
            "streak_three" -> bestStreak.coerceAtMost(3)
            "on_fire" -> bestStreak.coerceAtMost(5)
            "study_week" -> quizzesCompleted.coerceAtMost(7)
            "hundred_questions" -> totalAnswered.coerceAtMost(100)
            "pipo_champion" -> totalXp.coerceAtMost(250)
            "first_quiz" -> quizzesCompleted.coerceAtMost(1)
            "perfect_score" -> if (unlockedAchievements.contains("perfect_score")) 1 else 0
            "leveling_up" -> if (totalXp >= 50) 1 else 0
            "master_pipo" -> if (totalXp >= 300) 1 else 0
            "ai_friend" -> if (unlockedAchievements.contains("ai_friend")) 1 else 0
            else -> 0
        }
    }

    companion object {
        fun calculateLevel(xp: Int): LevelInfo {
            return when {
                xp < 50 -> LevelInfo(1, "Curioso", "🌱", 0, 50)
                xp < 150 -> LevelInfo(2, "Descobridor", "🔎", 50, 150)
                xp < 300 -> LevelInfo(3, "Aprendiz", "⚡", 150, 300)
                xp < 600 -> LevelInfo(4, "Mestre", "🧠", 300, 600)
                else -> LevelInfo(5, "Gênio", "💡", 600, 1200)
            }
        }
    }
}
