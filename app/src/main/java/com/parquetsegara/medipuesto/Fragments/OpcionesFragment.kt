package com.parquetsegara.medipuesto.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.parquetsegara.medipuesto.Adapters.AdapterMuebles
import com.parquetsegara.medipuesto.Adapters.AdapterTrabajo
import com.parquetsegara.medipuesto.Clases.Database
import com.parquetsegara.medipuesto.Clases.DatosRestauracion
import com.parquetsegara.medipuesto.Clases.MarcaBarniz
import com.parquetsegara.medipuesto.Clases.Mueble
import com.parquetsegara.medipuesto.MainActivity
import com.parquetsegara.medipuesto.databinding.FragmentOpcionesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
val marcaBona = MarcaBarniz("Bona", listOf("Acabado 2K", "Satinado", "Brillante", "Mate"))
val marcaWoca =
    MarcaBarniz("Woca", listOf("Acabado 2K", "Satinado", "Brillante", "Mate", "Invisible"))
val marcaCobba = MarcaBarniz("Lobba", listOf("Acabado 2K", "Satinado", "Brillante", "Mate"))
val marcaCotolW771 = MarcaBarniz("Cotol W771", listOf("Color", "IPE", "Natural", "Teka"))
val marcasBarniz = listOf(marcaBona, marcaWoca, marcaCobba, marcaCotolW771)

class OpcionesFragment : Fragment() {

    private lateinit var binding: FragmentOpcionesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpcionesBinding.inflate(inflater, container, false)
        return binding.root


        return view
    }

    private fun disableViewsAtStart() {
        binding.slctorTypeCirugia.isEnabled = false
        binding.slctorTypeSustucion.isEnabled = false
        binding.fieldM2Sustitucion.isEnabled = false
        binding.fieldM2CirugiaParquet.isEnabled = false
        binding.lytSlctorSustOpciones.visibility = View.GONE
        binding.lytSuperficieSustOpciones.visibility = View.GONE
        binding.lytCantidadCirugias.visibility = View.GONE
        binding.lytCantidadPiezasSust.visibility = View.GONE
    }

    private fun increaseValue(value: Int, editText: EditText) {
        editText.setText((value + 1).toString())
    }

    private fun decreaseValue(value: Int, editText: EditText) {
        if (value > 1) {
            editText.setText((value - 1).toString())
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disableViewsAtStart()

        binding.multilineMateriales.setOnTouchListener { v, event ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
            }
            false
        }


        var cantidadPiezas = 0
        var cantidadCirugias = 0
        binding.btnPlusCirugia.setOnClickListener {
            cantidadCirugias = binding.valorNumCirugias.text.toString().toInt()
            increaseValue(cantidadCirugias, binding.valorNumCirugias)
        }
        binding.btnMinusCirugia.setOnClickListener {
            cantidadCirugias = binding.valorNumCirugias.text.toString().toInt()
            decreaseValue(cantidadCirugias, binding.valorNumCirugias)
        }

        binding.btnPlusSust.setOnClickListener {
            cantidadPiezas = binding.valorNumPiezas.text.toString().toInt()
            increaseValue(cantidadPiezas, binding.valorNumPiezas)
        }
        binding.btnMinusSust.setOnClickListener {
            cantidadPiezas = binding.valorNumPiezas.text.toString().toInt()
            decreaseValue(cantidadPiezas, binding.valorNumPiezas)
        }


        binding.chckSustitucion.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.lytSlctorSustOpciones.visibility = View.VISIBLE
                binding.lytSuperficieSustOpciones.visibility = View.VISIBLE
                cantidadPiezas = 1
                binding.valorNumPiezas.setText(cantidadPiezas.toString())

                binding.slctorTypeSustucion.isEnabled = true
                binding.fieldM2Sustitucion.isEnabled = true
                binding.lytCantidadPiezasSust.visibility = View.VISIBLE
            } else {
                binding.slctorTypeSustucion.isEnabled = false
                binding.fieldM2Sustitucion.isEnabled = false
                binding.lytCantidadPiezasSust.visibility = View.GONE
                cantidadPiezas = 0
                binding.valorNumPiezas.setText(cantidadPiezas.toString())

                if (!binding.chckCirugia.isChecked) {
                    binding.lytCantidadPiezasSust.visibility = View.GONE
                    binding.lytSlctorSustOpciones.visibility = View.GONE
                    binding.lytSuperficieSustOpciones.visibility = View.GONE
                }
            }
        }
        binding.chckCirugia.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.lytSlctorSustOpciones.visibility = View.VISIBLE
                binding.lytSuperficieSustOpciones.visibility = View.VISIBLE
                cantidadCirugias = 1
                binding.valorNumCirugias.setText(cantidadCirugias.toString())

                binding.slctorTypeCirugia.isEnabled = true
                binding.fieldM2CirugiaParquet.isEnabled = true
                binding.lytCantidadCirugias.visibility = View.VISIBLE
            } else {
                binding.slctorTypeCirugia.isEnabled = false
                binding.fieldM2CirugiaParquet.isEnabled = false
                binding.lytCantidadCirugias.visibility = View.GONE
                cantidadCirugias = 0
                binding.valorNumCirugias.setText(cantidadCirugias.toString())

                if (!binding.chckSustitucion.isChecked) {
                    binding.lytCantidadPiezasSust.visibility = View.GONE
                    binding.lytSlctorSustOpciones.visibility = View.GONE
                    binding.lytSuperficieSustOpciones.visibility = View.GONE
                }
            }
        }

        var cantidad_pletinas = 3
        binding.btnPlusPletinas.setOnClickListener {
            cantidad_pletinas = binding.valorNumPletinas.text.toString().toInt()
            increaseValue(cantidad_pletinas, binding.valorNumPiezas)
        }
        binding.btnMinusPletinas.setOnClickListener {
            if (cantidad_pletinas > 3) {
                cantidad_pletinas -= 1
                binding.valorNumPletinas.setText(cantidad_pletinas.toString())
            }
        }

// Crear el adaptador de ArrayAdapter para el Spinner de marcas de barniz
        val adaptadorBarniz = this.context?.let {
            ArrayAdapter(it, android.R.layout.simple_spinner_item, marcasBarniz.map { it.nombre })
        }
        if (adaptadorBarniz != null) {
            adaptadorBarniz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spnerTypeBarniz.adapter =
                adaptadorBarniz // Establecer el adaptador en el Spinner
        }

// Establecer el listener para el Spinner de marcas de barniz
        binding.spnerTypeBarniz.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>, view: View?, position: Int, id: Long
                ) {
                    val marcaSeleccionada = marcasBarniz[position]
                    val tiposBarniz = marcaSeleccionada.opcionesAcabado

                    // Crear el adaptador de ArrayAdapter para el Spinner de tipos de barniz
                    val adaptadorTipoBarniz = ArrayAdapter(
                        requireContext(), android.R.layout.simple_spinner_item, tiposBarniz
                    )
                    adaptadorTipoBarniz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    // Establecer el adaptador en el Spinner de tipos de barniz
                    binding.spnerTypeAcabatBarniz.adapter = adaptadorTipoBarniz
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // No hacer nada
                }
            }

        var mueblesMarcados = mutableListOf<Mueble>()
        var adapter = AdapterMuebles(mueblesMarcados)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.btnAddMobles.setOnClickListener {
            mueblesMarcados += Mueble()
            adapter.notifyItemChanged(mueblesMarcados.size - 1)
        }


        var ubicacion = "NULL"
        binding.radiogroupEstiloZocalo.setOnCheckedChangeListener{group, checkecId ->
            val radioButton: RadioButton = binding.radiogroupUbicacion.findViewById(checkecId)
            ubicacion = radioButton.text.toString()
        }

        var estilo_zocalo = "NULL"
        binding.radiogroupEstiloZocalo.setOnCheckedChangeListener{group, checkecId ->
            val radioButton: RadioButton = binding.radiogroupEstiloZocalo.findViewById(checkecId)
            estilo_zocalo = radioButton.text.toString()
        }

        binding.btnSaveWork.setOnClickListener {
            Toast.makeText(requireContext(), "hy", Toast.LENGTH_LONG).show()
            /*Database.newRestauracion(
                DatosRestauracion(
                    binding.slcTypePaviment.selectedItem.toString(),
                    binding.slcTypeMadera.selectedItem.toString(),
                    ubicacion,

                    binding.multilineMateriales.text.toString(),

                    binding.chckSustitucion.isChecked,
                    binding.valorNumPiezas.text.toString().toInt(),
                    binding.slctorTypeSustucion.selectedItem.toString(),
                    binding.fieldM2Sustitucion.text.toString().toInt(),

                    binding.chckCirugia.isChecked,
                    binding.valorNumCirugias.text.toString().toInt(),
                    binding.slctorTypeCirugia.selectedItem.toString(),
                    binding.fieldM2CirugiaParquet.text.toString().toInt(),

                    binding.fieldM2Zocalo.text.toString().toInt(),
                    binding.slctTypeZocalo.selectedItem.toString(),
                    estilo_zocalo,
                    binding.valorNumPletinas.text.toString().toInt(),
                    binding.fieldM2Pletina.text.toString().toInt(),
                    binding.slctTypePletina.selectedItem.toString(),
                    "NULL ESTILO",
                    binding.spnerTypeBarniz.selectedItem.toString(),
                    binding.spnerTypeAcabatBarniz.selectedItem.toString()
                )
            )*/
        }
    }
    fun getValue(): String {
        return binding.slcTypeMadera.selectedItem.toString()
    }

}