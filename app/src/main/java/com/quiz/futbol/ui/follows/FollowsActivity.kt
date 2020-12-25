package com.quiz.futbol.ui.follows

import android.os.Bundle
import android.view.View
import com.quiz.futbol.R
import com.quiz.futbol.base.BaseActivity
import com.quiz.futbol.databinding.FollowsActivityBinding
import com.quiz.futbol.utils.Constants
import com.quiz.futbol.utils.setSafeOnClickListener

class FollowsActivity : BaseActivity() {
    private lateinit var binding: FollowsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FollowsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerFollows, FollowsFragment.newInstance())
                    .commitNow()
        }
        binding.appBar.btnBack.setSafeOnClickListener { finish() }
        binding.appBar.toolbarTitle.text = intent?.getStringExtra(Constants.FOLLOW_SCREEN)
        binding.appBar.layoutLife.visibility = View.GONE
    }
}