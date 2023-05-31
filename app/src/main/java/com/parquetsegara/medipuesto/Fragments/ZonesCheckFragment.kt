package com.parquetsegara.medipuesto.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.parquetsegara.medipuesto.Adapters.RecyclerViewZonas
import com.parquetsegara.medipuesto.Clases.Zona
import com.parquetsegara.medipuesto.databinding.ZonasRestauracionBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ZonesCheckFragment : Fragment() {

    private var _binding: ZonasRestauracionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val zonas = listOf(
        Zona("Entrada", 0),
        Zona("Sala de estar", 0),
        Zona("Cocina", 0),
        Zona("Comedor", 0),
        Zona("Lavabo", 0),
        Zona("Vestidor", 0),
        Zona("Despacho", 0),
        Zona("Dormitorio principal", 0),
        Zona("Dormitorio secundario", 0)
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ZonasRestauracionBinding.inflate(inflater, container, false)
        val recyclerView = binding.recyclerViewZonas

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerViewZonas(zonas)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreateWorkZone.setOnClickListener{
            val action = ZonesCheckFragmentDirections.actionRestauracionCheckFragmentToZonaTrabajo(zonas.toTypedArray())
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}