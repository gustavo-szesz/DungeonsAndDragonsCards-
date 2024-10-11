package com.ded.atributos

class DistribuicaoPontosPadrao : DistribuicaoPontosStrategy {
    override fun calcularCustoAtributo(valor: Int): Int {
        return when (valor) {
            8 -> 0
            9 -> 1
            10 -> 2
            11 -> 3
            12 -> 4
            13 -> 5
            14 -> 7
            15 -> 9
            else -> 0 // Valor fora do intervalo esperado
        }
    }
}