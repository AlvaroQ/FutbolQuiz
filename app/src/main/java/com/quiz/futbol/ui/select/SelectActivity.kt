package com.quiz.futbol.ui.select

import android.os.Bundle
import com.quiz.futbol.R
import com.quiz.futbol.base.BaseActivity
import com.quiz.futbol.databinding.SelectActivityBinding

class SelectActivity : BaseActivity() {
    private lateinit var binding: SelectActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerSelect, SelectFragment.newInstance())
                .commitNow()
        }
    }
}