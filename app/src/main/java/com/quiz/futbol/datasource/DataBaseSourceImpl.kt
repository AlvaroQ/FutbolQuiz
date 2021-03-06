package com.quiz.futbol.datasource

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.quiz.data.datasource.DataBaseSource
import com.quiz.domain.App
import com.quiz.domain.Stadium
import com.quiz.futbol.BuildConfig
import com.quiz.futbol.utils.Constants.PATH_REFERENCE_APPS
import com.quiz.futbol.utils.Constants.PATH_REFERENCE_FIRST_DIVISION
import com.quiz.futbol.utils.Constants.PATH_REFERENCE_SPAIN
import com.quiz.futbol.utils.Constants.PATH_REFERENCE_TEAMS
import com.quiz.futbol.utils.log
import kotlinx.coroutines.suspendCancellableCoroutine

class DataBaseSourceImpl : DataBaseSource {

    override suspend fun getStadiumById(id: Int, path_reference_championship: String): Stadium {
        log("GET STADIUM ID", PATH_REFERENCE_TEAMS + path_reference_championship + PATH_REFERENCE_FIRST_DIVISION + id)
        return suspendCancellableCoroutine { continuation ->
            FirebaseDatabase.getInstance().getReference(PATH_REFERENCE_TEAMS + path_reference_championship + PATH_REFERENCE_FIRST_DIVISION + id)
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