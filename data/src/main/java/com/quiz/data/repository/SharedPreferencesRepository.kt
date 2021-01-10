package com.quiz.data.repository

import com.quiz.data.datasource.SharedPreferencesLocalDataSource


class SharedPreferencesRepository(private val sharedPreferencesLocalDataSource: SharedPreferencesLocalDataSource)  {

    var paymentDone: Boolean
        get() = sharedPreferencesLocalDataSource.paymentDone
        set(value) {
            sharedPreferencesLocalDataSource.paymentDone = value
        }

    var uuid: String
        get() = sharedPreferencesLocalDataSource.uuid
        set(uuid) {
            sharedPreferencesLocalDataSource.uuid = uuid
        }

    var timestampGame: Long
        get() = sharedPreferencesLocalDataSource.timestampGame
        set(value) {
            sharedPreferencesLocalDataSource.timestampGame = value
        }

    var levelLocal: Int
        get() = sharedPreferencesLocalDataSource.levelLocal
        set(levelLocal) {
            sharedPreferencesLocalDataSource.levelLocal = levelLocal
        }
}