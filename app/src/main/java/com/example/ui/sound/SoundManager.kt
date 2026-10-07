package com.example.ui.sound

import android.media.AudioManager
import android.media.ToneGenerator
import android.util.Log

class SoundManager {

    private var toneGenerator: ToneGenerator? = null
    var isEnabled: Boolean = true

    init {
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 85)
        } catch (e: Exception) {
            Log.w("SoundManager", "ToneGenerator could not be created: ${e.message}")
        }
    }

    fun playClick() {
        if (!isEnabled) return
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 40)
        } catch (_: Exception) {}
    }

    fun playCorrect() {
        if (!isEnabled) return
        try {
            // High cheerful beep chime
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_ACK, 180)
        } catch (_: Exception) {}
    }

    fun playWrong() {
        if (!isEnabled) return
        try {
            // Soft friendly low tone
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_NACK, 140)
        } catch (_: Exception) {}
    }

    fun playStreakBonus() {
        if (!isEnabled) return
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200)
        } catch (_: Exception) {}
    }

    fun playCelebration() {
        if (!isEnabled) return
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_CDMA_NETWORK_USA_RINGBACK, 350)
        } catch (_: Exception) {}
    }

    fun release() {
        try {
            toneGenerator?.release()
            toneGenerator = null
        } catch (_: Exception) {}
    }
}
