package com.parquetsegara.medipuesto.Adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.parquetsegara.medipuesto.Fragments.OpcionesFragment

class AdapterTrabajo(fragment: Fragment, private val zonas: List<String>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = zonas.size

    override fun createFragment(position: Int): Fragment {
        return OpcionesFragment()
    }
}
