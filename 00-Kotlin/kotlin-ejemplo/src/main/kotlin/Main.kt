package org.example

import java.util.*

fun main(args: Array<String>) {
    // INMUTABLES (No se RE ASIGNA "=")
    val inmutable: String = "Andrés"
    //inmutable = "Andrés" // Error!
    // MUTABLES
    var mutable: String = "Edwin"
    mutable = "Andrés" // OK
    // VAL > VAR


    // Duck Typing
    val ejemploVariable = " Andrés Cantuña "
    ejemploVariable.trim()
    val edadEjemplo: Int = 24
    // ejemploVariable = edadEjemplo // Error!
    // Variables Primitivas
    val nombreProfesor: String = "Andrés Cantuña"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true
    // Clases en Java
    val fechaNacimiento: Date = Date()


    // When (Switch)
    val estadoCivilWhen = "S"
    when (estadoCivilWhen) {
        ("C") -> {
            println("Casado")
        }

        "S" -> {
            println("Soltero")
        }

        else -> {
            println("No sabemos")
        }
    }
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No" // if else chiquito

    imprimirNombre("Andrés")

    calcularSueldo(10.00) // solo parámetro requerido
    calcularSueldo(10.00, 15.00, 20.00) // parámetro requerido y sobreescribir parámetros opcionales
    // Named parameters
    // calcularSueldo(sueldo, tasa, bonoEspecial)
    calcularSueldo(10.00, bonoEspecial = 20.00) // usando el parámetro bonoEspecial en segunda posición
    // gracias a los parámetros nombrados
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)
    // usando el parámetro bonoEspecial en primera posición
    // usando el parámetro sueldo en segunda posición
    // usando el parámetro tasa en tercera posición
    // gracias a los parámetros nombrados
    val sumaA = Suma(1, 1)
    val sumaB = Suma(1, null)
    val sumaC = Suma(null, 1)
    val sumaD = Suma(null, null)
    sumaA.sumar()
    sumaB.sumar()
    sumaC.sumar()
    sumaD.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    // Arreglos
    // Estáticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
    println(arregloEstatico)

    // Dinámicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // FOR EACH => Unit
    // Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int -> // -> = >
            println("Valor actual: ${valorActual}");
        }
    // "it" (en inglés "eso") significa el elemento iterado
    arregloDinamico.forEach { println("Valor actual (it): ${it}") }

    // MAP => MUTA(Modifica cambia) el arreglo
    // 1) Enviamos el nuevo valor de la iteración
    // 2) Nos devuelve un NUEVO ARREGLO con valores de las iteraciones
    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15 }
    println(respuestaMapDos)

    // Filter => FILTRA el arreglo
    // 1) Devuelve una expresión (TRUE o FALSE)
    // 2) Nuevo arreglo FILTRADO
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            // Expresión o CONDICIÓN
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR & AND
    // OR -> ANY (Alguna cumple?)
    // AND -> ALL (Todas cumplen?)
    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any valorActual > 5
        }
    println(respuestaAny) // TRUE
    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all valorActual > 5
        }
    println(respuestaAll) // FALSE

    // REDUCE -> Acumulativo
    // Valor acumulado = 0 (Siempre empieza en 0 en Kotlin)
    // [1,2,3,4,5] -> Acumular "SUMAR" estos valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion1
    // valorIteracion2 = valorAcumuladoIteracion1 + 2 = 1 + 2 = 3 -> Iteracion2
    // valorIteracion3 = valorAcumuladoIteracion2 + 3 = 3 + 3 = 6 -> Iteracion3
    // valorIteracion4 = valorAcumuladoIteracion3 + 4 = 6 + 4 = 10 -> Iteracion4
    // valorIteracion5 = valorAcumuladoIteracion4 + 5 = 10 + 5 = 15 -> Iteracion5
    val respuestaReduce: Int = arregloDinamico
        .reduce { acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // -> Cambiar o usar la lógica de negocio
        }
    println(respuestaReduce)
    // return@reduce acumulado + (itemCarrito.cantidad * itemCarrito.precio)
}

// Funciones
fun imprimirNombre(nombre: String): Unit {
    fun otraFuncionAdentro(): Unit {
        println("Otra función adentro")
    }
    println("Nombre: $nombre") // Uso sin llaves
    println("Nombre: ${nombre}") // Uso con llaves (opcional)
    println("Nombre: ${nombre + nombre}") // Uso con llaves (concatenado)
    println("Nombre: ${nombre.toString()}") // Uso con llaves (función)
    println("Nombre: $nombre.toString()") // ¡INCORRECTO! (no puede usarse sin llaves)
    otraFuncionAdentro()
}

fun calcularSueldo(
    sueldo: Double, // Requerido
    tasa: Double = 12.00, // Opcional (defecto)
    bonoEspecial: Double? = null // Opcional (nullable)
    // ¿Variable? -> "?" Es Nullable (o sea que puede en algún momento ser nulo)
): Double {
    // Int -> Int? (nullable)
    // String -> String? (nullable)
    // Date -> Date? (nullable)
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) * bonoEspecial
    }
}

abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ) {
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros( // Constructor Primario
    //  Caso 1) Parámetro normal
    //  uno: Int, (parámetro (sin modificador acceso))

    //  Caso 2) Parámetro y propiedad (atributo) (protected)
    //  private var uno: Int (propiedad "instancia.uno")
    protected val numeroUno: Int, // instancia.numeroUno
    protected val numeroDos: Int, // instancia.numeroDos
    parametroNoUsadoNoPropiedadDeLaClase: Int? = null
) {
    init { // bloque constructor primario OPCIONAL
        this.numeroUno
        this.numeroDos
        println("Inicializando")
    }
}

class Suma(
    //Constructor primario
    unoParametro: Int, // Parámetro
    dosParametro: Int, // Parámetro
) : Numeros( // Clase padre, Números(extend)
    unoParametro, dosParametro
) {
    public val soyPublicoExplicito: String = "Públicas"
    val soyPublicoImplicito: String = "Público Implícito"

    init {  // Bloque constructor primario
        this.numeroUno
        this.numeroDos
        numeroUno // this. OPCIONAL [propiedades, métodos]
        numeroDos // this. OPCIONAL [propiedades, métodos]
        this.soyPublicoImplicito
        soyPublicoExplicito
    }

    constructor(
        // Constructor secundario
        uno: Int?,  // Entero nullable
        dos: Int,
    ) : this(
        if (uno == null) 0 else uno,
        dos
    ) {
        // Bloque de código del constructor secundario
    }

    constructor(
        // Constructor secundario
        uno: Int,
        dos: Int?, // Entero nullable
    ) : this(
        if (dos == null) 0 else dos,
        uno
    )

    constructor(
        // Constructor secundario
        uno: Int?,  // Entero nullable
        dos: Int?,  // Entero nullable
    ) : this(
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos
    )

    fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object { // Comparte entre todas las instancias, similar al STATIC
        // funciones, variables
        // NombreClase, método, NombreClase, función =>
        // Suma.pi
        val pi = 3.14

        // Suma.elevarAlCuadrado
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }

        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorTotalSuma: Int) {
            historialSumas.add(valorTotalSuma)
        }
    }

}