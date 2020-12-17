package com.quiz.data.repository

import com.quiz.data.datasource.SharedPreferencesLocalDataSource
import com.quiz.domain.User


class SharedPreferencesRepository(private val sharedPreferencesLocalDataSource: SharedPreferencesLocalDataSource)  {

    var paymentDone: Boolean
        get() = sharedPreferencesLocalDataSource.paymentDone
        set(value) {
            sharedPreferencesLocalDataSource.paymentDone = value
        }

    var currentUser: User
        get() = sharedPreferencesLocalDataSource.currentUser
        set(user) {
            sharedPreferencesLocalDataSource.currentUser = user
        }

}