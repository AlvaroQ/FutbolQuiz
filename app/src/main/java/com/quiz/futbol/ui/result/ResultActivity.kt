package com.quiz.futbol.ui.result

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.quiz.futbol.R
import com.quiz.futbol.base.BaseActivity
import com.quiz.futbol.common.startActivity
import com.quiz.futbol.databinding.ResultActivityBinding
import com.quiz.futbol.ui.select.SelectActivity
import com.quiz.futbol.utils.setSafeOnClickListener

class ResultActivity : BaseActivity() {
    lateinit var binding: ResultActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerResult, ResultFragment.newInstance())
                    .commitNow()
        }

        binding.appBar.toolbarTitle.text = getString(R.string.game_over)
        binding.appBar.layoutLife.visibility = View.GONE
        binding.appBar.btnBack.setSafeOnClickListener {
            startActivity<SelectActivity> {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }
    }

}