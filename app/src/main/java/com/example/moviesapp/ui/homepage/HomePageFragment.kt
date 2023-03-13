package com.example.moviesapp.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviesapp.databinding.FragmentHomePageBinding
import com.example.moviesapp.ui.homepage.nowplaying.NowPlayingFragment
import com.example.moviesapp.ui.homepage.popular.PopularFragment
import com.example.moviesapp.ui.homepage.toprated.TopRatedFragment
import com.example.moviesapp.ui.homepage.upcoming.UpcomingFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomePageFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentHomePageBinding.inflate(layoutInflater)
    }

    private val fragments = ArrayList<Fragment>()

    private val adapterVP by lazy {
        ViewPagerAdapter(this@HomePageFragment, fragments).also {
            binding.viewpager.adapter = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadTabLayout()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    private fun loadTabLayout() {
        fragments.add(NowPlayingFragment())
        fragments.add(UpcomingFragment())
        fragments.add(TopRatedFragment())
        fragments.add(PopularFragment())

        adapterVP

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Now playing"
                1 -> "Upcoming"
                2 -> "Top rated"
                3 -> "Popular"
                else -> ""
            }
        }.attach()
    }
}
