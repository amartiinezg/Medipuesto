package com.parquetsegara.medipuesto.Clases

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Zona constructor(var nombre: String?, var cantidad: Int?) : Parcelable {
    fun getZona(): String? {
        return nombre
    }

    fun getcantidad(): Int? {
        return cantidad
    }
}
