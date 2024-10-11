package com.ded.classes

enum class ClassePersonagem(val nomeExibicao: String, val habilidadePrimaria: String) {
    BARBARO("Bárbaro", "Força"),
    BARDO("Bardo", "Carisma"),
    CLERIGO("Clérigo", "Sabedoria"),
    DRUIDA("Druida", "Sabedoria"),
    GUERREIRO("Guerreiro", "Força ou Destreza"),
    MONJE("Monge", "Destreza e Sabedoria"),
    PALADINO("Paladino", "Força e Carisma"),
    PATRULHEIRO("Patrulheiro", "Destreza e Sabedoria"),
    LADINO("Ladino", "Destreza"),
    FEITICEIRO("Feiticeiro", "Carisma"),
    BRUXO("Bruxo", "Carisma"),
    MAGO("Mago", "Inteligência");

    override fun toString(): String {
        return nomeExibicao
    }
}