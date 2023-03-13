package com.example.moviesapp.ui.homepage

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragment: Fragment,
    private val fragmentsList: ArrayList<Fragment>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = fragmentsList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position]
    }
}
