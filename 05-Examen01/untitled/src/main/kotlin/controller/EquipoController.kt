package org.example.controller

import org.example.model.Equipo
import org.example.model.Futbolista
import org.example.utils.JsonUtil

class EquipoController {
    private val archivo = "equipos.json"
    private var equipos: ArrayList<Equipo> = arrayListOf()

    init {
        try {
            equipos = JsonUtil.leerArchivo(archivo)
        } catch (e: Exception) {
            println("Archivo no encontrado o vacío, inicializando lista de equipos.")
        }
    }

    private fun guardarCambios() {
        JsonUtil.escribirArchivo(archivo, equipos)
    }

    fun crearEquipo(equipo: Equipo) {
        equipos.add(equipo)
        guardarCambios()
    }

    fun obtenerTodosLosEquipos(): List<Equipo> {
        return equipos
    }

    fun obtenerEquipo(nombre: String): Equipo? {
        return equipos.find { it.nombre == nombre }
    }

    fun editarEquipo(nombre: String, updatedEquipo: Equipo): Boolean {
        val indice = equipos.indexOfFirst { it.nombre == nombre }
        return if (indice != -1) {
            equipos[indice] = updatedEquipo
            guardarCambios()
            true
        } else {
            false
        }
    }

    fun eliminarEquipo(nombre: String): Boolean {
        val equipoEliminado = equipos.removeIf { it.nombre == nombre }
        if (equipoEliminado) {
            guardarCambios()
        }
        return equipoEliminado
    }

    fun agregarFutbolistaAEquipo(nombreEquipo: String, futbolista: Futbolista): Boolean {
        val equipo = obtenerEquipo(nombreEquipo)
        return if (equipo != null) {
            equipo.plantilla.add(futbolista)
            guardarCambios()
            true
        } else {
            false
        }
    }

    fun editarFutbolistaDeEquipo(nombreEquipo: String, nombreFutbolista: String, futbolistaActualizado: Futbolista): Boolean {
        val equipo = obtenerEquipo(nombreEquipo)
        return if (equipo != null) {
            val indice = equipo.plantilla.indexOfFirst { it.nombre == nombreFutbolista }
            if (indice != -1) {
                equipo.plantilla[indice] = futbolistaActualizado
                guardarCambios()
                true
            } else {
                false
            }
        } else {
            false
        }
    }

    fun eliminarFutbolistaDeEquipo(nombreEquipo: String, nombreFutbolista: String): Boolean {
        val equipo = obtenerEquipo(nombreEquipo)
        return if (equipo != null) {
            val eliminado = equipo.plantilla.removeIf { it.nombre == nombreFutbolista }
            if (eliminado) {
                guardarCambios()
            }
            eliminado
        } else {
            false
        }
    }

}