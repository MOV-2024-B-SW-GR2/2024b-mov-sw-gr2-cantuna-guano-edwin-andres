package org.example.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.serializersModuleOf
import java.io.File
import java.util.*

object JsonUtil {
    val json = Json {
        serializersModule = serializersModuleOf(Date::class, DateSerializer)
    }

    inline fun <reified T> leerArchivo(filePath: String): T {
        val contenido = File(filePath).readText()
        return json.decodeFromString(contenido)
    }

    inline fun <reified T> escribirArchivo(archivo: String, data: T) {
        val cadenaJson = json.encodeToString(data)
        File(archivo).writeText(cadenaJson)
    }
}