package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.AchievementCategory
import com.example.data.model.DefaultAchievements
import com.example.data.model.Subject
import com.example.data.model.SubjectStat
import com.example.data.model.UserProfile
import com.example.data.repository.QuestionBank
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("PIPO", appName)
    }

    @Test
    fun `question bank returns valid questions for subject and age`() {
        val mathQuestions = QuestionBank.getQuestionsFor(Subject.MATEMATICA, 7, count = 10)
        assertEquals(10, mathQuestions.size)
        mathQuestions.forEach { q ->
            assertTrue(q.options.contains(q.correctAnswer))
            assertTrue(q.options.size >= 3)
        }
    }

    @Test
    fun `user level calculates accurately from XP`() {
        val level1 = UserProfile.calculateLevel(20)
        assertEquals(1, level1.levelNumber)

        val level2 = UserProfile.calculateLevel(80)
        assertEquals(2, level2.levelNumber)

        val level5 = UserProfile.calculateLevel(700)
        assertEquals(5, level5.levelNumber)
    }

    @Test
    fun `achievements contain learning, engagement, and challenge categories`() {
        val all = DefaultAchievements.getAll()
        assertTrue(all.any { it.id == "math_master" && it.category == AchievementCategory.LEARNING })
        assertTrue(all.any { it.id == "speed_reader" && it.category == AchievementCategory.LEARNING })
        assertTrue(all.any { it.id == "study_week" && it.category == AchievementCategory.ENGAGEMENT })
        assertTrue(all.any { it.id == "streak_three" && it.category == AchievementCategory.ENGAGEMENT })
        assertTrue(all.any { it.id == "perfect_score" && it.category == AchievementCategory.CHALLENGE })
        assertTrue(all.any { it.id == "hundred_questions" && it.category == AchievementCategory.ENGAGEMENT })
    }

    @Test
    fun `achievement progress calculates accurately from user profile`() {
        val profile = UserProfile(
            totalAnswered = 45,
            bestStreak = 4,
            quizzesCompleted = 3,
            subjectStats = mapOf(
                Subject.MATEMATICA.id to SubjectStat(answered = 15, correct = 12)
            )
        )

        assertEquals(12, profile.getAchievementProgress("math_master"))
        assertEquals(45, profile.getAchievementProgress("hundred_questions"))
        assertEquals(3, profile.getAchievementProgress("streak_three"))
    }
}
