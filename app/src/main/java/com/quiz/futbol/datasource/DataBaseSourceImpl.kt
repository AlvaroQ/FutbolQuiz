package com.quiz.futbol.datasource

import com.quiz.futbol.utils.Constants.PATH_REFERENCE_COUNTRIES
import com.quiz.futbol.utils.Constants.PATH_REFERENCE_APPS
import com.quiz.data.datasource.DataBaseSource
import com.quiz.domain.Stadium
import com.quiz.futbol.utils.log
import com.quiz.domain.App
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.suspendCancellableCoroutine
import com.google.firebase.database.ktx.getValue
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.quiz.futbol.BuildConfig

class DataBaseSourceImpl : DataBaseSource {

    override suspend fun getStadiumById(id: Int): Stadium {
        return suspendCancellableCoroutine { continuation ->
            FirebaseDatabase.getInstance().getReference(PATH_REFERENCE_COUNTRIES + id)
                .addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        continuation.resume(dataSnapshot.getValue(Stadium::class.java) as Stadium){}
                    }

                    override fun onCancelled(error: DatabaseError) {
                        log("getCountryById FAILED", "Failed to read value.", error.toException())
                        continuation.resume(Stadium()){}
                        FirebaseCrashlytics.getInstance().recordException(Throwable(error.toException()))
                    }
                })
        }
    }

    override suspend fun getAppsRecommended(): MutableList<App> {
        return suspendCancellableCoroutine { continuation ->
            FirebaseDatabase.getInstance().getReference(PATH_REFERENCE_APPS)
                .addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var value = dataSnapshot.getValue<MutableList<App>>()
                        if(value == null) value = mutableListOf()
                        continuation.resume(value.filter { it.url != BuildConfig.APPLICATION_ID }.toMutableList()){}
                    }

                    override fun onCancelled(error: DatabaseError) {
                        log("DataBaseBaseSourceImpl", "Failed to read value.", error.toException())
                        continuation.resume(mutableListOf()){}
                        FirebaseCrashlytics.getInstance().recordException(Throwable(error.toException()))
                    }
                })
        }
    }
}