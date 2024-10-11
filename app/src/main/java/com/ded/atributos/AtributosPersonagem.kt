import Raca.Raca

data class AtributosPersonagens(
    val forca: Int = 0,
    val destreza: Int = 0,
    val constituicao: Int = 0,
    val sabedoria: Int = 0,
    val inteligencia: Int = 0,
    val carisma: Int = 0
) {
    fun toString(race: Raca): String {
        val totalForca = forca + (race.bonus["Força"] ?: 0)
        val totalDestreza = destreza + (race.bonus["Destreza"] ?: 0)
        val totalConstituicao = constituicao + (race.bonus["Constituição"] ?: 0)
        val totalSabedoria = sabedoria + (race.bonus["Sabedoria"] ?: 0)
        val totalInteligencia = inteligencia + (race.bonus["Inteligência"] ?: 0)
        val totalCarisma = carisma + (race.bonus["Carisma"] ?: 0)

        return "Força: $totalForca, Destreza: $totalDestreza, Constituição: $totalConstituicao, Sabedoria: $totalSabedoria, Inteligência: $totalInteligencia, Carisma: $totalCarisma"
    }
}