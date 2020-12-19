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
import com.quiz.futbol.utils.Constants.COLLECTION_USERS
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirestoreDataSourceImpl(private val database: FirebaseFirestore) : FirestoreDataSource {

    override suspend fun signInUser(user: User): Either<RepositoryException, User> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_USERS)
                .document(user.uuid!!)
                .set(user)
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
            val ref = database
                .collection(COLLECTION_USERS)
                .document(uuid)

            ref.get()
                .addOnSuccessListener {
                    val user: User = it.toObject<User>()!!
                    continuation.resume(user.right())
                }
                .addOnFailureListener {
                    continuation.resume(RepositoryException.NoConnectionException.left())
                    FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                }
        }
    }
}
