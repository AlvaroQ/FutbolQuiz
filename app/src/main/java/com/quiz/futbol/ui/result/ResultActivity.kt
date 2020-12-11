package com.quiz.futbol.ui.result

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.quiz.futbol.BuildConfig
import com.quiz.futbol.R
import com.quiz.futbol.base.BaseActivity
import com.quiz.futbol.common.startActivity
import com.quiz.futbol.ui.select.SelectActivity
import com.quiz.futbol.utils.log
import com.quiz.futbol.utils.setSafeOnClickListener
import kotlinx.android.synthetic.main.app_bar_layout.*

class ResultActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerResult, ResultFragment.newInstance())
                .commitNow()
        }

        btnBack.setSafeOnClickListener {
            startActivity<SelectActivity> {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }
        toolbarTitle.text = getString(R.string.resultado_screen_title)
        layoutLife.visibility = View.GONE
    }

}