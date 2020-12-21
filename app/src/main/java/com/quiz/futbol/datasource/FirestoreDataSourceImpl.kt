package com.quiz.futbol.datasource

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.quiz.data.datasource.FirestoreDataSource
import com.quiz.data.repository.RepositoryException
import com.quiz.domain.User
import com.quiz.futbol.utils.Constants.COLLECTION_FOLLOWERS
import com.quiz.futbol.utils.Constants.COLLECTION_FOLLOWING
import com.quiz.futbol.utils.Constants.COLLECTION_MAIN_ARCHIEVEMENTS
import com.quiz.futbol.utils.Constants.COLLECTION_OTHER_ARCHIEVEMENTS
import com.quiz.futbol.utils.Constants.COLLECTION_USERS
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirestoreDataSourceImpl(private val database: FirebaseFirestore) : FirestoreDataSource {

    override suspend fun signInUser(user: User): Either<RepositoryException, User> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_USERS).document(user.uuid!!).set(user)
                    .addOnSuccessListener {
                        continuation.resume(user.right())
                    }
                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }

    override suspend fun getUserFromUuid(uuid: String): Either<RepositoryException, User> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_USERS).document(uuid).get()
                    .addOnSuccessListener {
                        continuation.resume(it.toObject<User>()!!.right())
                    }
                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }

    override suspend fun getFollowers(uuid: String): Either<RepositoryException, Int> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_FOLLOWERS).document(uuid).get()
                    .addOnSuccessListener {
                        if(it.data == null) continuation.resume(0.right())
                        else continuation.resume((it.data!!["uuid"] as ArrayList<*>).size.right())
                    }
                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }

    override suspend fun getFollowing(uuid: String): Either<RepositoryException, Int> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_FOLLOWING).document(uuid).get()
                    .addOnSuccessListener {
                        if(it.data == null) continuation.resume(0.right())
                        else continuation.resume((it.data!!["uuid"] as ArrayList<*>).size.right())
                    }
                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }

    override suspend fun getLevel(uuid: String): Either<RepositoryException, Int> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_OTHER_ARCHIEVEMENTS).document(uuid).get()
                    .addOnSuccessListener {
                        val numberDocuments = it.data
                        if(numberDocuments == null) continuation.resume(0.right())
                        else continuation.resume(numberDocuments.size.right())
                    }
                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }

    override suspend fun getMainArchievements(uuid: String): Either<RepositoryException, MutableList<String>> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_MAIN_ARCHIEVEMENTS).document(uuid).get()
                    .addOnSuccessListener {
                        val numberDocuments = it.data
                        if(numberDocuments == null) continuation.resume(mutableListOf("").right())
                        else continuation.resume(numberDocuments.keys.toMutableList().right())
                    }
                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }

    }
}
