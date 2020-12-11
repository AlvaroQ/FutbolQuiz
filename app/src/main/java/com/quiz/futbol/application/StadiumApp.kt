package com.quiz.futbol.application

import android.app.Application

class StadiumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}