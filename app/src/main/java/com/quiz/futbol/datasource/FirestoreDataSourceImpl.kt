package com.quiz.futbol.datasource

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.quiz.data.datasource.FirestoreDataSource
import com.quiz.data.models.ArchievementsBack
import com.quiz.data.repository.RepositoryException
import com.quiz.domain.Archievements
import com.quiz.domain.User
import com.quiz.futbol.utils.Constants.COLLECTION_ARCHIEVEMENTS
import com.quiz.futbol.utils.Constants.COLLECTION_FOLLOWERS
import com.quiz.futbol.utils.Constants.COLLECTION_FOLLOWING
import com.quiz.futbol.utils.Constants.COLLECTION_USERS
import com.quiz.futbol.utils.Constants.STAGE_COMPLETED
import com.quiz.futbol.utils.log
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

    override suspend fun getCountFollowers(uuid: String): Either<RepositoryException, Int> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_FOLLOWERS).document(uuid).get()
                    .addOnSuccessListener {
                        if(it.data == null) continuation.resume(0.right())
                        else continuation.resume(it.data!!.size.right())
                    }
                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }

    override suspend fun getCountFollowing(uuid: String): Either<RepositoryException, Int> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_FOLLOWING).document(uuid).get()
                    .addOnSuccessListener {
                        if(it.data == null) continuation.resume(0.right())
                        else continuation.resume(it.data!!.size.right())
                    }
                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }

    override suspend fun getFollowing(uuid: String): Either<RepositoryException, MutableList<User>> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_FOLLOWING).document(uuid).get()
                    .addOnSuccessListener { it ->
                        val result: MutableList<User> = mutableListOf()
                        it.data?.keys?.forEach { result.add(User(uuid = it)) }
                        continuation.resume(result.right())
                    }

                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }

    override suspend fun getIsFollowingThisUser(myUuid: String, uuid: String): Either<RepositoryException, Boolean> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_FOLLOWING).document(myUuid).get()
                    .addOnSuccessListener { it ->
                        var result = false
                        it.data?.keys?.forEach { if(uuid == it) result = true }
                        continuation.resume(result.right())
                    }

                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }

    override suspend fun getFollowers(uuid: String): Either<RepositoryException, MutableList<User>> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_FOLLOWERS).document(uuid).get()
                    .addOnSuccessListener { it ->
                        val result: MutableList<User> = mutableListOf()
                        it.data?.keys?.forEach { result.add(User(uuid = it)) }
                        continuation.resume(result.right())
                    }

                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }

    override suspend fun setFollower(isFollow: Boolean, fromUuid: String, toUuid: String): Either<RepositoryException, Boolean> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_FOLLOWERS)
                .document(fromUuid)
                .set( { toUuid to isFollow } )
                .addOnSuccessListener { continuation.resume(true.right()) }
                .addOnFailureListener {
                    continuation.resume(RepositoryException.NoConnectionException.left())
                    FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                }
        }
    }

    override suspend fun setFollowing(isFollow: Boolean, fromUuid: String, toUuid: String): Either<RepositoryException, Boolean> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_FOLLOWING)
                .document(toUuid)
                .set( { fromUuid to isFollow } )
                .addOnSuccessListener { continuation.resume(true.right()) }
                .addOnFailureListener {
                    continuation.resume(RepositoryException.NoConnectionException.left())
                    FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                }
        }
    }

    override suspend fun getUserLevel(uuid: String): Either<RepositoryException, Int> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_USERS).document(uuid).get()
                    .addOnSuccessListener {
                        continuation.resume(it.toObject<User>()!!.progressUser.right())
                    }
                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }

    override suspend fun getUserStageCompleted(uuid: String): Either<RepositoryException, MutableList<String>> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_ARCHIEVEMENTS)
                    .whereEqualTo("userUid", uuid)
                    .whereEqualTo("typeGame", STAGE_COMPLETED)
                    .get()
                    .addOnSuccessListener { documents ->
                        val result: MutableList<String> = mutableListOf()
                        for (document in documents) {
                            result.add(document.data["typeChampionship"].toString())
                        }
                        continuation.resume(result.right())
                    }
                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }

    }

    override suspend fun getGlobalArchievements(): Either<RepositoryException, MutableList<ArchievementsBack>> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_ARCHIEVEMENTS).orderBy("createdAt", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener { documents ->
                        val result: MutableList<ArchievementsBack> = mutableListOf()
                        for (document in documents) {
                            result.add(ArchievementsBack(
                                    userUid = document.data["userUid"].toString(),
                                    typeChampionship = document.data["typeChampionship"].toString(),
                                    typeGame = document.data["typeGame"].toString(),
                                    createdAt = document.data["createdAt"] as Long,
                                    points = document.data["points"] as Long))
                        }
                        continuation.resume(result.right())
                    }
                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }

    override suspend fun getPersonalArchievements(uuid: String): Either<RepositoryException, MutableList<ArchievementsBack>> {
        return suspendCancellableCoroutine { continuation ->
            database.collection(COLLECTION_ARCHIEVEMENTS).whereEqualTo("userUid", uuid).orderBy ("createdAt", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener { documents ->
                        val result: MutableList<ArchievementsBack> = mutableListOf()
                        for (document in documents) {
                            result.add(ArchievementsBack(
                                    userUid = document.data["userUid"].toString(),
                                    typeChampionship = document.data["typeChampionship"].toString(),
                                    typeGame = document.data["typeGame"].toString(),
                                    createdAt = document.data["createdAt"] as Long,
                                    points = document.data["points"] as Long))
                        }
                        continuation.resume(result.right())
                    }
                    .addOnFailureListener {
                        continuation.resume(RepositoryException.NoConnectionException.left())
                        FirebaseCrashlytics.getInstance().recordException(Throwable(it.cause))
                    }
        }
    }
}
