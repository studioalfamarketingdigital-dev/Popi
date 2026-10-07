package com.example.ui.sound

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class TtsManager(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private var isInitialized = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.forLanguageTag("pt-BR"))
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Fallback to general Portuguese or default
                tts?.setLanguage(Locale.forLanguageTag("pt"))
            }
            tts?.setSpeechRate(0.92f) // slightly slower, friendlier tempo for kids
            tts?.setPitch(1.15f) // slightly higher, friendly animated voice pitch
            isInitialized = true
        } else {
            Log.w("TtsManager", "TTS initialization failed with status: $status")
        }
    }

    fun speak(text: String) {
        if (isInitialized && tts != null) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "PIPO_TTS_${System.currentTimeMillis()}")
        }
    }

    fun stop() {
        try {
            tts?.stop()
        } catch (_: Exception) {}
    }

    fun release() {
        try {
            tts?.stop()
            tts?.shutdown()
            tts = null
        } catch (_: Exception) {}
    }
}
