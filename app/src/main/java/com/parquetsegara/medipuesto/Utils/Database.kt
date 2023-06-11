package com.parquetsegara.medipuesto.Clases

import android.annotation.SuppressLint
import android.util.Log
import kotlinx.coroutines.*
import com.google.firebase.firestore.FirebaseFirestore
import com.parquetsegara.medipuesto.Fragments.OpcionesFragment
import kotlinx.coroutines.tasks.await

class Database {
    companion object {
        @SuppressLint("StaticFieldLeak")
        val db = FirebaseFirestore.getInstance()

        fun newRestauracion(data: DatosRestauracion){
            val restauracionRef = db.collection("Restauración")
            val datosRestauracion = hashMapOf(
                "Pavimiento" to data.pavimiento, "Madera" to data.madera, "Ubicación" to data.ubicacion,
                "Material" to data.material,
                "Sustitución" to hashMapOf("Checked" to data.sust_piezas, "Cantidad" to data.num_piezas, "Tipo" to data.tipo_pieza, "Medida" to data.medida_pieza),
                "Cirugía" to hashMapOf("Checked" to data.cirug_piezas, "Cantidad" to data.num_cirug, "Tipo" to data.tipo_cirug, "Medida" to data.medida_cirug),
                "Zócalo" to hashMapOf("Medida" to data.medida_zocalo, "Tipo" to data.tipo_zocalo, "Estilo" to data.estilo_zocalo),
                "Pletina" to hashMapOf("Cantidad" to data.num_pletinas, "Medida" to data.medida_pletina, "Tipo" to data.tipo_pletina, "Estilo" to data.estilo_pletina),
                "Barniz" to hashMapOf("Tipo" to data.tipo_barniz, "Estilo" to data.estilo_barniz),
            )
            restauracionRef.add(datosRestauracion)
        }
    }


}