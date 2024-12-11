package org.example.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
class Equipo (
    val nombre: String,
    @Contextual val fechaFundacion: Date,
    val numeroSocios: Int,
    val presupuesto: Double,
    val estadio: String,
    val plantilla: ArrayList<Futbolista>
)