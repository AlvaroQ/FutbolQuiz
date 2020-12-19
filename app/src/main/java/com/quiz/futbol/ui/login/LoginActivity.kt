package com.quiz.futbol.ui.login

import android.os.Bundle
import com.quiz.futbol.R
import com.quiz.futbol.base.BaseActivity
import com.quiz.futbol.databinding.LoginActivityBinding

class LoginActivity : BaseActivity() {
    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerLogin, LoginFragment.newInstance())
                .commitNow()
        }
    }
}