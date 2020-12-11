package com.quiz.futbol.ui.ranking

import android.os.Bundle
import android.view.View
import com.quiz.futbol.R
import com.quiz.futbol.base.BaseActivity
import com.quiz.futbol.utils.setSafeOnClickListener
import kotlinx.android.synthetic.main.app_bar_layout.*

class RankingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerResult, RankingFragment.newInstance())
                .commitNow()
        }

        btnBack.setSafeOnClickListener {
            finish()
        }
        toolbarTitle.text = getString(R.string.ranking_screen_title)
        layoutLife.visibility = View.GONE
    }
}