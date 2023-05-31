package com.parquetsegara.medipuesto.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.parquetsegara.medipuesto.Adapters.AdapterTrabajo
import com.parquetsegara.medipuesto.Clases.Zona
import com.parquetsegara.medipuesto.R
import com.parquetsegara.medipuesto.databinding.TrabajoFragmentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ZonaTrabajo.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrabajoFragment : Fragment(R.layout.trabajo_fragment) {

    private lateinit var binding: TrabajoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TrabajoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val zonasSelected = mutableListOf<String>() // Crear lista vacía

        if (arguments != null) {
            val zonasChecked = arguments?.let { TrabajoFragmentArgs.fromBundle(it) }?.zonas?.filterIsInstance<Zona>() ?.toTypedArray()
            if (zonasChecked != null) {
                for (zona in zonasChecked) {
                    if (zona.cantidad != 0) {
                        zona.cantidad?.let {
                            repeat(it) { i ->
                                val zonaString = "${zona.nombre} ${i + 1}"
                                zonasSelected.add(zonaString) // Agregar zona a la lista
                            }
                        }
                    }
                }
            }
        } else {
        }


        binding.viewPager.adapter = AdapterTrabajo(this, zonasSelected)
        TabLayoutMediator(binding.tabLayout, binding.viewPager)
        { tab, position ->
            tab.text = zonasSelected[position]
        }.attach()
    }
}
