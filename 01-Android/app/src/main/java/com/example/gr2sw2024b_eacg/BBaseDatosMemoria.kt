package com.example.gr2sw2024b_eacg

class BBaseDatosMemoria {
    companion object {
        var arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador.add(BEntrenador(1, "Andres", "a@a.com"))
            arregloBEntrenador.add(BEntrenador(1, "Edwin", "b@b.com"))
            arregloBEntrenador.add(BEntrenador(1, "Nancy", "c@c.com"))
        }
    }
}