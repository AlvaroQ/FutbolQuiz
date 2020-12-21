package com.quiz.data.repository

import arrow.core.Either
import com.quiz.data.datasource.FirestoreDataSource
import com.quiz.domain.User

class UserRepository(private val firestoreDataSource: FirestoreDataSource) {
    suspend fun signInUser(user: User) = firestoreDataSource.signInUser(user)
    suspend fun getUserFromUuid(uuid: String): Either<RepositoryException, User> = firestoreDataSource.getUserFromUuid(uuid)
    suspend fun getFollowers(uuid: String): Either<RepositoryException, Int> = firestoreDataSource.getFollowers(uuid)
    suspend fun getFollowing(uuid: String): Either<RepositoryException, Int> = firestoreDataSource.getFollowing(uuid)
    suspend fun getLevel(uuid: String): Either<RepositoryException, Int> = firestoreDataSource.getLevel(uuid)
    suspend fun getMainArchievements(uuid: String): Either<RepositoryException, MutableList<String>> = firestoreDataSource.getMainArchievements(uuid)
}
