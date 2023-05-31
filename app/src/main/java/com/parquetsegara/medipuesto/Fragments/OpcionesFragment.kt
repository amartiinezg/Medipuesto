package com.parquetsegara.medipuesto.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.parquetsegara.medipuesto.Adapters.AdapterMuebles
import com.parquetsegara.medipuesto.Clases.MarcaBarniz
import com.parquetsegara.medipuesto.Clases.Mueble
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
        binding.slctorTypeSusttucion.isEnabled = false
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

        binding.editTextMultiLine.setOnTouchListener { v, event ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
            }
            false
        }


        var cantidadPiezas = 0
        var cantidadCirugias = 0
        binding.btnPlusCirugia.setOnClickListener {
            cantidadCirugias = binding.editTextCirugia.text.toString().toInt()
            increaseValue(cantidadCirugias, binding.editTextCirugia)
        }
        binding.btnMinusCirugia.setOnClickListener {
            cantidadCirugias = binding.editTextCirugia.text.toString().toInt()
            decreaseValue(cantidadCirugias, binding.editTextCirugia)
        }

        binding.btnPlusSust.setOnClickListener {
            cantidadPiezas = binding.editTextSust.text.toString().toInt()
            increaseValue(cantidadPiezas, binding.editTextSust)
        }
        binding.btnMinusSust.setOnClickListener {
            cantidadPiezas = binding.editTextSust.text.toString().toInt()
            decreaseValue(cantidadPiezas, binding.editTextSust)
        }


        binding.chckSustitucion.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.lytSlctorSustOpciones.visibility = View.VISIBLE
                binding.lytSuperficieSustOpciones.visibility = View.VISIBLE
                cantidadPiezas = 1
                binding.editTextSust.setText(cantidadPiezas.toString())

                binding.slctorTypeSusttucion.isEnabled = true
                binding.fieldM2Sustitucion.isEnabled = true
                binding.lytCantidadPiezasSust.visibility = View.VISIBLE
            } else {
                binding.slctorTypeSusttucion.isEnabled = false
                binding.fieldM2Sustitucion.isEnabled = false
                binding.lytCantidadPiezasSust.visibility = View.GONE
                cantidadPiezas = 0
                binding.editTextSust.setText(cantidadPiezas.toString())

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
                binding.editTextCirugia.setText(cantidadCirugias.toString())

                binding.slctorTypeCirugia.isEnabled = true
                binding.fieldM2CirugiaParquet.isEnabled = true
                binding.lytCantidadCirugias.visibility = View.VISIBLE
            } else {
                binding.slctorTypeCirugia.isEnabled = false
                binding.fieldM2CirugiaParquet.isEnabled = false
                binding.lytCantidadCirugias.visibility = View.GONE
                cantidadCirugias = 0
                binding.editTextCirugia.setText(cantidadCirugias.toString())

                if (!binding.chckSustitucion.isChecked) {
                    binding.lytCantidadPiezasSust.visibility = View.GONE
                    binding.lytSlctorSustOpciones.visibility = View.GONE
                    binding.lytSuperficieSustOpciones.visibility = View.GONE
                }
            }
        }

        var cantidad_pletinas = 3
        binding.btnPlusPletinas.setOnClickListener {
            cantidad_pletinas = binding.editTextPletinas.text.toString().toInt()
            increaseValue(cantidad_pletinas, binding.editTextSust)
        }
        binding.btnMinusPletinas.setOnClickListener {
            if (cantidad_pletinas > 3) {
                cantidad_pletinas -= 1
                binding.editTextPletinas.setText(cantidad_pletinas.toString())
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
            adapter.notifyItemChanged(mueblesMarcados.size-1)
        }
    }

}