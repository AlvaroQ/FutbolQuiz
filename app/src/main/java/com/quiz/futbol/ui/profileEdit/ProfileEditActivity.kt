package com.quiz.futbol.ui.profileEdit

import android.os.Bundle
import android.view.View
import com.quiz.futbol.R
import com.quiz.futbol.base.BaseActivity
import com.quiz.futbol.databinding.EditProfileActivityBinding
import com.quiz.futbol.utils.setSafeOnClickListener

class ProfileEditActivity : BaseActivity() {
    private lateinit var binding: EditProfileActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditProfileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerEditProfile, ProfileEditFragment.newInstance())
                    .commitNow()
        }

        binding.appBar.btnBack.setSafeOnClickListener { finish() }
        binding.appBar.toolbarTitle.text = getString(R.string.edit_profile)
        binding.appBar.layoutLife.visibility = View.GONE
    }
}