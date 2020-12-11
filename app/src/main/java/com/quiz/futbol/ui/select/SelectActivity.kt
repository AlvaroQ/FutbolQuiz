package com.quiz.futbol.ui.select

import android.os.Bundle
import com.quiz.futbol.R
import com.quiz.futbol.base.BaseActivity

class SelectActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerSelect, SelectFragment.newInstance())
                .commitNow()
        }
    }
}