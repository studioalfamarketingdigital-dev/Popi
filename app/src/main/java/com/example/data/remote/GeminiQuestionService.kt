package com.example.data.remote

import android.util.Log
import com.example.BuildConfig
import com.example.data.model.Difficulty
import com.example.data.model.Question
import com.example.data.model.Subject
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.UUID
import java.util.concurrent.TimeUnit

@JsonClass(generateAdapter = true)
data class GeminiPart(
    val text: String? = null
)

@JsonClass(generateAdapter = true)
data class GeminiContent(
    val parts: List<GeminiPart>
)

@JsonClass(generateAdapter = true)
data class GeminiRequest(
    val contents: List<GeminiContent>
)

@JsonClass(generateAdapter = true)
data class GeminiCandidate(
    val content: GeminiContent?
)

@JsonClass(generateAdapter = true)
data class GeminiResponse(
    val candidates: List<GeminiCandidate>?
)

@JsonClass(generateAdapter = true)
data class GeneratedQuestionDto(
    val question: String? = null,
    val options: List<String>? = null,
    val correctAnswer: String? = null,
    val explanation: String? = null,
    val subject: String? = null,
    val difficulty: String? = null,
    val ageRange: String? = null
)

interface GeminiApi {
    @POST("v1beta/models/gemini-3.5-flash:generateContent")
    suspend fun generateContent(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}

class GeminiQuestionRepository {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        })
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://generativelanguage.googleapis.com/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val api: GeminiApi = retrofit.create(GeminiApi::class.java)

    suspend fun generateEducationalQuestion(
        subject: Subject,
        age: Int,
        topicHint: String? = null
    ): Question? = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            Log.d("GeminiRepo", "GEMINI_API_KEY is not set or placeholder. Using local question bank.")
            return@withContext null
        }

        val subjectName = if (subject == Subject.DESAFIO_PIPO) "Conhecimentos Gerais ou Matemática" else subject.title
        val ageBand = when {
            age <= 7 -> "6-7 anos (conteúdo introdutório, lúdico, com linguagem simples e acolhedora)"
            age <= 9 -> "8-9 anos (conteúdo intermediário, estimulante)"
            else -> "10 anos (conteúdo avançado, problemas e lógica)"
        }

        val prompt = """
            Você é o assistente pedagógico do aplicativo infantil PIPO (Aprender brincando).
            Gere UMA pergunta educativa de múltipla escolha para crianças de $ageBand.
            Matéria: $subjectName.
            ${if (!topicHint.isNullOrBlank()) "Foque ou reforce o assunto: $topicHint." else ""}
            
            REGRAS CRÍTICAS:
            - A pergunta e todas as 4 alternativas devem estar em PORTUGUÊS (Brasil).
            - Totalmente seguro para crianças, lúdico, factual e sem ambiguidades.
            - Responda ESTRITAMENTE em formato JSON puro, sem markdown nem ```json```.
            - Estrutura obrigatória:
            {
              "question": "texto da pergunta clara",
              "options": ["opção 1", "opção 2", "opção 3", "opção 4"],
              "correctAnswer": "a mesma opção correta exatamente igual a um dos itens de options",
              "explanation": "explicação carinhosa e didática de por que essa é a resposta certa",
              "subject": "${subject.id}",
              "difficulty": "facil",
              "ageRange": "$age"
            }
        """.trimIndent()

        try {
            val response = api.generateContent(
                apiKey = apiKey,
                request = GeminiRequest(
                    contents = listOf(
                        GeminiContent(
                            parts = listOf(GeminiPart(text = prompt))
                        )
                    )
                )
            )

            val rawText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                ?: return@withContext null

            parseQuestionJson(rawText, subject, age)
        } catch (e: Exception) {
            Log.w("GeminiRepo", "Gemini API request failed, falling back to local bank: ${e.message}")
            null
        }
    }

    private fun parseQuestionJson(raw: String, requestedSubject: Subject, age: Int): Question? {
        return try {
            // Clean up possible markdown code fences
            val cleaned = raw.replace("```json", "")
                .replace("```", "")
                .trim()

            val adapter = moshi.adapter(GeneratedQuestionDto::class.java)
            val dto = adapter.fromJson(cleaned) ?: return null

            if (dto.question.isNullOrBlank() ||
                dto.options.isNullOrEmpty() ||
                dto.options.size < 2 ||
                dto.correctAnswer.isNullOrBlank() ||
                !dto.options.contains(dto.correctAnswer)
            ) {
                return null
            }

            Question(
                id = "ai_${UUID.randomUUID().toString().take(8)}",
                question = dto.question,
                options = dto.options,
                correctAnswer = dto.correctAnswer,
                explanation = dto.explanation ?: "Muito bem! Você acertou!",
                subject = requestedSubject,
                difficulty = Difficulty.MEDIO,
                ageRange = "$age",
                isAiGenerated = true
            )
        } catch (e: Exception) {
            Log.w("GeminiRepo", "Failed to parse question JSON: ${e.message}")
            null
        }
    }
}
