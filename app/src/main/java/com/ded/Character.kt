package com.ded

import Raca.Raca
import com.ded.classes.ClassePersonagem
import AtributosPersonagens

data class Character(
    val name: String,
    val race: Raca,
    val characterClass: ClassePersonagem,
    val atributos: AtributosPersonagens
)