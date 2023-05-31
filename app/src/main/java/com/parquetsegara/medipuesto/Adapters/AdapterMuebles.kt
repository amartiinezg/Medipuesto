package com.parquetsegara.medipuesto.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.parquetsegara.medipuesto.Clases.Mueble
import com.parquetsegara.medipuesto.Fragments.marcasBarniz
import com.parquetsegara.medipuesto.R

class AdapterMuebles(val muebles: MutableList<Mueble>) :
    RecyclerView.Adapter<AdapterMuebles.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val spinner: Spinner = itemView.findViewById(R.id.spner_typeMueble)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar_mueble)
        val lytMuebles: LinearLayout = itemView.findViewById(R.id.lyt_muebles)

        init {
// Crear el adaptador de ArrayAdapter para el Spinner de marcas de barniz
            val adaptadorSpinner = spinner.context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item,
                    Mueble.listaMuebles.map { it.nombre })
            }
            if (adaptadorSpinner != null) {
                adaptadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter =
                    adaptadorSpinner // Establecer el adaptador en el Spinner
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_mobles, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Establecer el OnItemSelectedListener en el Spinner
        holder.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selected = Mueble.listaMuebles[position]
                muebles[holder.position].nombre = selected.nombre
                muebles[holder.position].modulo = selected.modulo
                holder.spinner.setSelection(Mueble.listaMuebles.indexOf(muebles[holder.position]))

                if (selected.modulo == 0){
                    holder.ratingBar.visibility = View.GONE
                }
                else{
                    holder.ratingBar.isEnabled = true
                    holder.ratingBar.visibility = View.VISIBLE
                }
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se seleccionó ningún elemento
            }
        }
    }

    override fun getItemCount(): Int = muebles.size
}