package com.quiz.data.datasource

import arrow.core.Either
import com.quiz.data.models.ArchievementsBack
import com.quiz.data.repository.RepositoryException
import com.quiz.domain.Archievements
import com.quiz.domain.User


interface FirestoreDataSource {
    suspend fun signInUser(user: User): Either<RepositoryException, User>
    suspend fun getUserFromUuid(uuid: String): Either<RepositoryException, User>
    suspend fun getCountFollowers(uuid: String): Either<RepositoryException, Int>
    suspend fun getFollowers(uuid: String): Either<RepositoryException, MutableList<User>>
    suspend fun setFollower(isFollow: Boolean, fromUuid: String, toUuid: String): Either<RepositoryException, Boolean>
    suspend fun setFollowing(isFollow: Boolean, fromUuid: String, toUuid: String): Either<RepositoryException, Boolean>
    suspend fun getCountFollowing(uuid: String): Either<RepositoryException, Int>
    suspend fun getFollowing(uuid: String): Either<RepositoryException, MutableList<User>>
    suspend fun getIsFollowingThisUser(myUuid: String, uuid: String): Either<RepositoryException, Boolean>
    suspend fun getUserLevel(uuid: String): Either<RepositoryException, Int>
    suspend fun getUserStageCompleted(uuid: String): Either<RepositoryException, MutableList<String>>
    suspend fun getGlobalArchievements(): Either<RepositoryException, MutableList<ArchievementsBack>>
    suspend fun getPersonalArchievements(uuid: String): Either<RepositoryException, MutableList<ArchievementsBack>>
}