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
        }
    }


}