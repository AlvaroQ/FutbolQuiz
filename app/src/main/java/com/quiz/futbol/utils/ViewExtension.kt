package com.quiz.futbol.utils

import android.media.MediaPlayer
import android.view.View
import android.view.animation.AnimationUtils
import com.quiz.futbol.R
import com.quiz.futbol.utils.listener.SafeClickListener


fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.scale_xy_collapse))
        MediaPlayer.create(context, R.raw.click).start()
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}
fun View.setSafeWithoutAnimationOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        MediaPlayer.create(context, R.raw.click).start()
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}