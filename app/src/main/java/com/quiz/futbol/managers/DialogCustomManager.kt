package com.quiz.futbol.managers

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import com.quiz.futbol.R
import com.quiz.futbol.databinding.DialogLockedBinding
import com.quiz.futbol.databinding.DialogSignInBinding
import com.quiz.futbol.utils.setSafeOnClickListener


class DialogCustomManager(val activity: Activity) {

    fun showDialogSignInWithGoogle(dialogSignInClick: () -> Unit = {}) {
        Dialog(activity, R.style.CustomDialog).apply {
            val binding: DialogSignInBinding = DialogSignInBinding.inflate(LayoutInflater.from(context))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(binding.root)
            binding.btnSignInButton.setSafeOnClickListener {
                cancel()
                dialogSignInClick()
            }
            show()
        }
    }

    fun showDialogLevelLock() {
        Dialog(activity, R.style.CustomDialog).apply {
            val binding: DialogLockedBinding = DialogLockedBinding.inflate(LayoutInflater.from(context))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(binding.root)
            binding.buttonCancel.setSafeOnClickListener { cancel() }
            show()
        }
    }
}