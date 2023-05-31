package com.parquetsegara.medipuesto.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.parquetsegara.medipuesto.R
import com.parquetsegara.medipuesto.databinding.HomeFragmentBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNewBudget.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("¿Qué tipo de trabajo desea realizar?")
            var workSelected = 1

            val adapter = object : ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_single_choice, arrayOf("Instalación", "Restauración")) {
                override fun areAllItemsEnabled(): Boolean {
                    return false
                }

                override fun isEnabled(position: Int): Boolean {
                    return position != 0 // deshabilitar la opción de instalación
                }
            }

            builder.setSingleChoiceItems(adapter, 1) { dialog, which ->
                workSelected = which
            }

            builder.setPositiveButton("Aceptar") { dialog, which ->
                when (workSelected.toString()) {
                    "0" -> {
                        // La opción de instalación está deshabilitada
                    }
                    "1" -> {
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                    }
                }
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

            builder.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}