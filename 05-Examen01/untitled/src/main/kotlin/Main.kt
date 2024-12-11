package org.example

import org.example.controller.EquipoController
import org.example.model.Equipo
import org.example.model.Futbolista
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.JOptionPane

fun main() {
    val equipoController = EquipoController()
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")

    while (true) {
        val opciones = arrayOf(
            "1. Agregar Equipo",
            "2. Mostrar Equipos",
            "3. Ver Plantilla de Equipo",
            "4. Editar Equipo",
            "5. Eliminar Equipo",
            "6. Salir"
        )
        val seleccion = JOptionPane.showOptionDialog(
            null,
            "Seleccione una opción",
            "Gestión de Equipos",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            opciones,
            opciones[0]
        )

        when (seleccion + 1) {
            1 -> {
                val nombre = JOptionPane.showInputDialog("Ingrese el nombre del equipo:")
                val fechaStr = JOptionPane.showInputDialog("Ingrese la fecha (dd/MM/yyyy):")
                val fechaFundacion: Date = dateFormat.parse(fechaStr)
                val numeroSocios = JOptionPane.showInputDialog("Ingrese el número de socios:").toInt()
                val presupuesto = JOptionPane.showInputDialog("Ingrese el presupuesto:").toDouble()
                val estadio = JOptionPane.showInputDialog("Ingrese el nombre del estadio:")
                val equipo = Equipo(nombre, fechaFundacion, numeroSocios, presupuesto, estadio, ArrayList())
                equipoController.crearEquipo(equipo)
                JOptionPane.showMessageDialog(null, "Equipo creado exitosamente")
            }

            2 -> {
                val equipos = equipoController.obtenerTodosLosEquipos()
                val equiposStr = equipos.joinToString("\n") {
                    "${it.nombre} - ${dateFormat.format(it.fechaFundacion)} - ${it.numeroSocios} - ${it.presupuesto} - ${it.estadio}"
                }
                JOptionPane.showMessageDialog(null, "Equipos:\n$equiposStr")
            }

            3 -> {
                val nombreEquipo = JOptionPane.showInputDialog("Ingrese el nombre del equipo:")
                val equipo = equipoController.obtenerEquipo(nombreEquipo)
                if (equipo != null) {
                    while (true) {
                        val jugadoresStr = equipo.plantilla.joinToString("\n") {
                            "${it.dorsal} - ${it.nombre} - ${dateFormat.format(it.fechaNacimiento)} - ${it.salario} - ${it.esTransferible}"
                        }
                        val opcionesJugadores = arrayOf(
                            "1. Agregar Jugador",
                            "2. Editar Jugador",
                            "3. Eliminar Jugador",
                            "4. Volver al menú principal"
                        )
                        val seleccionJugador = JOptionPane.showOptionDialog(
                            null,
                            "Plantilla de $nombreEquipo:\n$jugadoresStr",
                            "Gestión de Jugadores",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            opcionesJugadores,
                            opcionesJugadores[0]
                        )

                        when (seleccionJugador + 1) {
                            1 -> {
                                val nombreFutbolista = JOptionPane.showInputDialog("Ingrese el nombre del futbolista:")
                                val dorsal = JOptionPane.showInputDialog("Ingrese el dorsal del futbolista:").toInt()
                                val fechaNacimientoStr =
                                    JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del futbolista (dd/MM/yyyy):")
                                val fechaNacimiento: Date = dateFormat.parse(fechaNacimientoStr)
                                val salario =
                                    JOptionPane.showInputDialog("Ingrese el salario del futbolista:").toDouble()
                                val esTransferible = JOptionPane.showConfirmDialog(
                                    null,
                                    "¿Es transferible?",
                                    "Transferible",
                                    JOptionPane.YES_NO_OPTION
                                ) == JOptionPane.YES_OPTION
                                val futbolista =
                                    Futbolista(dorsal, nombreFutbolista, fechaNacimiento, salario, esTransferible)
                                equipoController.agregarFutbolistaAEquipo(nombreEquipo, futbolista)
                                JOptionPane.showMessageDialog(null, "Futbolista agregado exitosamente")
                            }

                            2 -> {
                                val nombreFutbolista =
                                    JOptionPane.showInputDialog("Ingrese el nombre del futbolista a editar:")
                                val futbolista = equipo.plantilla.find { it.nombre == nombreFutbolista }
                                if (futbolista != null) {
                                    val nombre = JOptionPane.showInputDialog("Nombre:", futbolista.nombre)
                                    val dorsal = JOptionPane.showInputDialog("Dorsal:", futbolista.dorsal).toInt()
                                    val fechaNacimientoStr =
                                        JOptionPane.showInputDialog("Fecha de nacimiento:")
                                    val fechaNacimiento: Date = dateFormat.parse(fechaNacimientoStr)
                                    val salario = JOptionPane.showInputDialog("Salario:", futbolista.salario).toDouble()
                                    val esTransferible = JOptionPane.showConfirmDialog(
                                        null,
                                        "¿Es transferible?",
                                        "Transferible",
                                        JOptionPane.YES_NO_OPTION
                                    ) == JOptionPane.YES_OPTION
                                    val futbolistaActualizado = Futbolista(dorsal, nombre, fechaNacimiento, salario, esTransferible)
                                    val editado = equipoController.editarFutbolistaDeEquipo(nombreEquipo, nombreFutbolista, futbolistaActualizado)
                                    if (editado) {
                                        JOptionPane.showMessageDialog(null, "Futbolista editado exitosamente")
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Futbolista no encontrado")
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Futbolista no encontrado")
                                }
                            }

                            3 -> {
                                val nombreFutbolista =
                                    JOptionPane.showInputDialog("Ingrese el nombre del futbolista a eliminar:")
                                val eliminado = equipoController.eliminarFutbolistaDeEquipo(nombreEquipo, nombreFutbolista)
                                if (eliminado) {
                                    JOptionPane.showMessageDialog(null, "Futbolista eliminado exitosamente")
                                } else {
                                    JOptionPane.showMessageDialog(null, "Futbolista no encontrado")
                                }
                            }

                            4 -> {
                                break
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Equipo no encontrado")
                }
            }

            4 -> {
                val equipoElegido = JOptionPane.showInputDialog("Ingrese el nombre del equipo a editar:")
                val nombre = JOptionPane.showInputDialog("Nombre del equipo:")
                val fechaStr = JOptionPane.showInputDialog("Fecha de fundación (dd/MM/yyyy):")
                val fechaFundacion: Date = dateFormat.parse(fechaStr)
                val numeroSocios = JOptionPane.showInputDialog("Número de socios:").toInt()
                val presupuesto = JOptionPane.showInputDialog("Presupuesto:").toDouble()
                val estadio = JOptionPane.showInputDialog("Estadio:")
                val equipo = Equipo(nombre, fechaFundacion, numeroSocios, presupuesto, estadio, ArrayList())
                val editado = equipoController.editarEquipo(equipoElegido, equipo)
                if (editado) {
                    JOptionPane.showMessageDialog(null, "Equipo editado exitosamente")
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error al editar el equipo")
                }
            }

            5 -> {
                val nombre = JOptionPane.showInputDialog("Ingrese el nombre del equipo que desea eliminar:")
                val eliminado = equipoController.eliminarEquipo(nombre)
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Equipo eliminado exitosamente")
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error al eliminar el equipo")
                }
            }

            6 -> {
                break
            }
        }
    }
}