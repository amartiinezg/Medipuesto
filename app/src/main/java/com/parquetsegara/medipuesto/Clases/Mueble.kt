package com.parquetsegara.medipuesto.Clases



data class Mueble(
    var nombre: String? = null,
    var modulo: Int? = null
) {
    fun getnombre(): String? {
        return nombre
    }

    fun getmodulo(): Int? {
        return modulo
    }

    companion object {
        val listaMuebles = listOf(
            Mueble("Silla", 0),
            Mueble("Mesa", 3),
            Mueble("Estanteria", 3),
            Mueble("Mueble", 3),
            Mueble("Sofá", 3),
            Mueble("Cama", 3),
            Mueble("Canape", 3),
            Mueble("Otros", 3)
        )
    }
}
