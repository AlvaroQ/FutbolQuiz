package com.quiz.data.datasource

import arrow.core.Either
import com.quiz.data.repository.RepositoryException
import com.quiz.domain.User


interface FirestoreDataSource {
    suspend fun signInUser(user: User): Either<RepositoryException, User>
    suspend fun getUserFromUuid(uuid: String): Either<RepositoryException, User>
    suspend fun getFollowers(uuid: String): Either<RepositoryException, Int>
    suspend fun getFollowing(uuid: String): Either<RepositoryException, Int>
    suspend fun getLevel(uuid: String): Either<RepositoryException, Int>
    suspend fun getMainArchievements(uuid: String): Either<RepositoryException, MutableList<String>>
}