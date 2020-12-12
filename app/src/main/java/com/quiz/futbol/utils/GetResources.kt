package com.quiz.futbol.utils

import android.app.Application
import androidx.core.content.ContextCompat

class GetResources(private val context: Application){
    fun getString(idString: Int) = context.getString(idString)
    fun getDrawable(idDrawable: Int) = ContextCompat.getDrawable(context, idDrawable)
}