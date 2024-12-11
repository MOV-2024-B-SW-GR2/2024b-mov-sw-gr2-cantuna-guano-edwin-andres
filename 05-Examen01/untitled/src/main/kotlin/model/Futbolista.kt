package org.example.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
class Futbolista (
    var dorsal: Int,
    var nombre: String,
    @Contextual var fechaNacimiento: Date,
    var salario: Double,
    var esTransferible: Boolean
)
