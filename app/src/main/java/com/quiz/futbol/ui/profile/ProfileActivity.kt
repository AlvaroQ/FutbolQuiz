package com.quiz.futbol.ui.profile

import android.os.Bundle
import com.quiz.futbol.R
import com.quiz.futbol.base.BaseActivity
import com.quiz.futbol.databinding.ProfileActivityBinding
import com.quiz.futbol.utils.setSafeOnClickListener

class ProfileActivity : BaseActivity() {
    private lateinit var binding: ProfileActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerProfile, ProfileFragment.newInstance())
                    .commitNow()
        }

        binding.appBar.btnBack.setSafeOnClickListener { finish() }
    }
}