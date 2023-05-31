package com.parquetsegara.medipuesto.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.parquetsegara.medipuesto.Clases.Zona
import com.parquetsegara.medipuesto.R

class RecyclerViewZonas(private val zonas: List<Zona>) :
    RecyclerView.Adapter<RecyclerViewZonas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.zonas_checkbox_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val zona = zonas[position]
        holder.bind(zona)
    }

    override fun getItemCount() = zonas.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreZona = itemView.findViewById<CheckBox>(R.id.checkBoxZona)
        private val cantidad = itemView.findViewById<TextView>(R.id.tvCantidad)
        private val btnRestar = itemView.findViewById<Button>(R.id.btnRestar)
        private val btnSumar = itemView.findViewById<Button>(R.id.btnSumar)

        fun bind(zona: Zona) {
            btnRestar.visibility = View.GONE
            cantidad.visibility = View.GONE
            btnSumar.visibility = View.GONE
            nombreZona.setOnClickListener{
                if(nombreZona.isChecked){
                    btnRestar.visibility = View.VISIBLE
                    cantidad.visibility = View.VISIBLE
                    zona.cantidad = 1
                    cantidad.setText("1")
                    btnSumar.visibility = View.VISIBLE

                }
                else{
                    btnRestar.visibility = View.GONE
                    cantidad.visibility = View.GONE
                    zona.cantidad = 0
                    btnSumar.visibility = View.GONE
                }
            }

            nombreZona.text = zona.nombre
            cantidad.text = zona.cantidad.toString()

            btnRestar.setOnClickListener {
                if (zona.cantidad!! > 1) {
                    zona.cantidad = zona.cantidad?.minus(1)
                    cantidad.text = zona.cantidad.toString()
                }
            }

            btnSumar.setOnClickListener {
                zona.cantidad = zona.cantidad?.plus(1)
                cantidad.text = zona.cantidad.toString()
            }
        }
    }
}
