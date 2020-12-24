package com.quiz.futbol.managers

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.quiz.data.datasource.SharedPreferencesLocalDataSource

open class SharedPrefsDataSource(context: Context): SharedPreferencesLocalDataSource {
    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
        context
    )

    override var paymentDone: Boolean
        get() = sharedPreferences.getBoolean(PAYMENT_DONE, false)
        set(value) = sharedPreferences.edit().putBoolean(PAYMENT_DONE, value).apply()

    override var uuid: String
        get() = sharedPreferences.getString(UUID, "")!!
        set(value) = sharedPreferences.edit().putString(UUID, value).apply()

    companion object {
        const val PAYMENT_DONE = "payment_done"
        const val UUID = "uuid"
    }
}