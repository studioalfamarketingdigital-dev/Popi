package com.example.data.model

enum class Subject(
    val id: String,
    val title: String,
    val icon: String,
    val description: String,
    val colorHex: Long
) {
    PORTUGUES(
        id = "portugues",
        title = "Português",
        icon = "📚",
        description = "Brinque com palavras, rimas e histórias!",
        colorHex = 0xFF3A86FF
    ),
    MATEMATICA(
        id = "matematica",
        title = "Matemática",
        icon = "🔢",
        description = "Desafie seus números e contas!",
        colorHex = 0xFF19D96F
    ),
    CIENCIAS(
        id = "ciencias",
        title = "Ciências",
        icon = "🔬",
        description = "Descubra animais, plantas e o corpo humano!",
        colorHex = 0xFF00C49F
    ),
    GEOGRAFIA(
        id = "geografia",
        title = "Geografia",
        icon = "🌎",
        description = "Explore cidades, rios, mapas e o planeta!",
        colorHex = 0xFFFF9F1C
    ),
    HISTORIA(
        id = "historia",
        title = "História",
        icon = "📖",
        description = "Viaje pelo tempo e conheça grandes descobertas!",
        colorHex = 0xFF9B51E0
    ),
    CONHECIMENTOS_GERAIS(
        id = "gerais",
        title = "Conhecimentos Gerais",
        icon = "🧠",
        description = "Curiosidades divertidas sobre o mundo!",
        colorHex = 0xFFFF6584
    ),
    DESAFIO_PIPO(
        id = "desafio_pipo",
        title = "Desafio Pipo",
        icon = "🎲",
        description = "Uma mistura mágica de todas as matérias!",
        colorHex = 0xFF39FF88
    );

    companion object {
        fun fromId(id: String): Subject {
            return entries.firstOrNull { it.id.equals(id, ignoreCase = true) } ?: MATEMATICA
        }
    }
}

enum class Difficulty(val label: String) {
    FACIL("Fácil"),
    MEDIO("Médio"),
    DIFICIL("Desafiador")
}

data class Question(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val explanation: String,
    val subject: Subject,
    val difficulty: Difficulty = Difficulty.FACIL,
    val ageRange: String = "6-7",
    val isAiGenerated: Boolean = false
)
