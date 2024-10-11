package Raca

enum class Raca(val nome: String, val bonus: Map<String, Int>) {
    HUMANO("Humano", mapOf("Força" to 1, "Destreza" to 1, "Constituição" to 1, "Inteligência" to 1, "Sabedoria" to 1, "Carisma" to 1)),
    ELFO("Elfo", mapOf("Destreza" to 2, "Inteligência" to 1)),
    ELFO_NEGRO_DROW("Elfo Negro 'Drow'", mapOf("Carisma" to 1)),
    ANAO("Anão", mapOf("Constituição" to 2, "Sabedoria" to 1)),
    MEIO_ORC("Meio-Orc", mapOf("Força" to 2, "Constituição" to 1)),
    DRACONATO("Draconato", mapOf("Força" to 2, "Carisma" to 1)),
    GNOMO("Gnomo", mapOf("Inteligência" to 2)),
    GNOMO_DA_FLORESTA("Gnomo da Floresta", mapOf("Destreza" to 1)),
    GNOMO_DA_PEDRA("Gnomo da Pedra", mapOf("Constituição" to 1));

    override fun toString(): String {
        return nome
    }
}
