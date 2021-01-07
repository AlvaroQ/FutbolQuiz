package com.quiz.futbol.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.quiz.futbol.R
import com.quiz.futbol.base.BaseActivity
import com.quiz.futbol.databinding.ActivityMainBinding
import com.quiz.futbol.ui.login.LoginFragment

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController
    lateinit var activity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onBackPressed() {
        when(navController.currentDestination?.id) {
            R.id.navigation_game -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_game_to_select)
            R.id.navigation_result -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_result_to_select)
            R.id.navigation_profile -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_profile_to_select)
            R.id.navigation_profile_edit -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_edit_profile_to_profile)
            R.id.navigation_follows -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_follow_to_profile)
            else -> finish()
        }
    }
}