package com.quiz.futbol.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.quiz.futbol.R
import com.quiz.futbol.base.BaseActivity
import com.quiz.futbol.databinding.ActivityMainBinding
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.futbol.ui.login.LoginFragment
import com.quiz.futbol.utils.log

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController
    private lateinit var mInterstitialAd: InterstitialAd
    lateinit var activity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this
        navController = findNavController(R.id.nav_host_fragment)
        showBannerAd()
    }

    override fun onBackPressed() {
        when(navController.currentDestination?.id) {
            R.id.navigation_game -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_game_to_select)
            R.id.navigation_result -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_result_to_select)
            R.id.navigation_profile -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_profile_to_select)
            R.id.navigation_profile_edit -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_edit_profile_to_profile)
            R.id.navigation_follows -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_follow_to_profile)
            R.id.navigation_more_apps -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_more_apps_to_select)
            else -> finish()
        }
    }

    private fun showBannerAd() {
        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    fun showInstersticialAd() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            log("TAG", "The interstitial wasn't loaded yet.")
            FirebaseCrashlytics.getInstance().recordException(Throwable("The interstitial wasn't loaded"))
        }
    }

    fun loadInstersticialAd() {
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(R.string.INTERSTICIAL_GAME)
        mInterstitialAd.loadAd(AdRequest.Builder().build())
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SHOW_AD_INTERSTICIAL)
    }
}