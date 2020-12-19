package com.quiz.data.repository

import arrow.core.Either
import com.quiz.data.datasource.FirestoreDataSource
import com.quiz.domain.User

class UserRepository(private val firestoreDataSource: FirestoreDataSource) {
    suspend fun signInUser(user: User) = firestoreDataSource.signInUser(user)
    suspend fun getUserFromUuid(uuid: String): Either<RepositoryException, User> = firestoreDataSource.getUserFromUuid(uuid)
}
