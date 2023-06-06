package com.parquetsegara.medipuesto.Clases

data class DatosRestauracion(
    val pavimiento: String, val madera: String, val ubicacion: String,
    val material: String,
    val sust_piezas: Boolean, val num_piezas: Int, val tipo_pieza: String, val medida_pieza: Int,
    val cirug_piezas: Boolean, val num_cirug: Int, val tipo_cirug: String, val medida_cirug: Int,
    val medida_zocalo: Int, val tipo_zocalo: String, val estilo_zocalo: String,
    val num_pletinas: Int, val medida_pletina: Int, val tipo_pletina: String, val estilo_pletina: String,
    val tipo_barniz: String, val estilo_barniz: String,
    )
