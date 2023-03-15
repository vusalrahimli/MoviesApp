package com.example.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.moviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setBottomNav()
    }

    private fun setBottomNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        navController = navHostFragment.navController

        binding.apply {
            val menuItems = arrayOf(
                CbnMenuItem(
                    R.drawable.home_icon,
                    R.drawable.avd_home,
                    R.id.homePageFragment,
                ),
                CbnMenuItem(
                    R.drawable.favorite_icon,
                    R.drawable.avd_watch_list,
                    R.id.watchListFragment,
                ),
            )

            bottomNav.setMenuItems(menuItems, 0)

            binding.bottomNav.setupWithNavController(navController)
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailFragment, R.id.personDetails, R.id.reviewBottomSheet,
                R.id.castFragment, R.id.reviewsFragment, R.id.aboutMovieFragment,
                -> hideBottomNav()

                else -> showBottomNav()
            }
        }
    }

    private fun hideBottomNav() {
        binding.bottomNav.isVisible = false
    }

    private fun showBottomNav() {
        binding.bottomNav.isVisible = true
    }
}
