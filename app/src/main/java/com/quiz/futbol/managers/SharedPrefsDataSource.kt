package com.quiz.futbol.managers

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.quiz.data.datasource.SharedPreferencesLocalDataSource
import com.quiz.domain.User

open class SharedPrefsDataSource(context: Context): SharedPreferencesLocalDataSource {
    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
        context
    )

    override var paymentDone: Boolean
        get() = sharedPreferences.getBoolean(PAYMENT_DONE, false)
        set(value) = sharedPreferences.edit().putBoolean(PAYMENT_DONE, value).apply()

    override var currentUser: User
        get() = Gson().fromJson(sharedPreferences.getString(CURRENT_USER, null), User::class.java)
        set(value) = sharedPreferences.edit().putString(CURRENT_USER, Gson().toJson(value)).apply()

    companion object {
        const val PAYMENT_DONE = "payment_done"
        const val CURRENT_USER = "current_user"
    }
}