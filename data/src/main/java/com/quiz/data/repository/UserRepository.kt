package com.quiz.data.repository

import arrow.core.Either
import com.quiz.data.datasource.FirestoreDataSource
import com.quiz.data.models.ArchievementsBack
import com.quiz.domain.Archievements
import com.quiz.domain.User

class UserRepository(private val firestoreDataSource: FirestoreDataSource) {
    suspend fun signInUser(user: User) = firestoreDataSource.signInUser(user)
    suspend fun getUserFromUuid(uuid: String): Either<RepositoryException, User> = firestoreDataSource.getUserFromUuid(uuid)
    suspend fun getCountFollowers(uuid: String): Either<RepositoryException, Int> = firestoreDataSource.getCountFollowers(uuid)
    suspend fun setUnfollower(fromUuid: String, toUuid: String): Either<RepositoryException, Boolean> = firestoreDataSource.setFollower(false, fromUuid, toUuid)
    suspend fun setFollower(fromUuid: String, toUuid: String): Either<RepositoryException, Boolean> = firestoreDataSource.setFollower(true, fromUuid, toUuid)
    suspend fun setUnfollowing(fromUuid: String, toUuid: String): Either<RepositoryException, Boolean> = firestoreDataSource.setFollowing(false, fromUuid, toUuid)
    suspend fun setFollowing(fromUuid: String, toUuid: String): Either<RepositoryException, Boolean> = firestoreDataSource.setFollowing(true, fromUuid, toUuid)
    suspend fun getFollowers(uuid: String): Either<RepositoryException, MutableList<User>> = firestoreDataSource.getFollowers(uuid)
    suspend fun getCountFollowing(uuid: String): Either<RepositoryException, Int> = firestoreDataSource.getCountFollowing(uuid)
    suspend fun getFollowing(uuid: String): Either<RepositoryException, MutableList<User>> = firestoreDataSource.getFollowing(uuid)
    suspend fun getIsFollowingThisUser(myUuid: String, uuid: String): Either<RepositoryException, Boolean> = firestoreDataSource.getIsFollowingThisUser(myUuid, uuid)
    suspend fun getUserLevel(uuid: String): Either<RepositoryException, Int> = firestoreDataSource.getUserLevel(uuid)
    suspend fun setUserLevel(uuid: String, level: Int): Either<RepositoryException, Int> = firestoreDataSource.setUserLevel(uuid, level)
    suspend fun getUserStageCompleted(uuid: String): Either<RepositoryException, MutableList<String>> = firestoreDataSource.getUserStageCompleted(uuid)
    suspend fun setUserStageCompleted(archievementBack: ArchievementsBack): Either<RepositoryException, Boolean> = firestoreDataSource.setUserStageCompleted(archievementBack)
    suspend fun getGlobalArchievements(): Either<RepositoryException, MutableList<ArchievementsBack>> = firestoreDataSource.getGlobalArchievements()
    suspend fun getPersonalArchievements(uuid: String): Either<RepositoryException, MutableList<ArchievementsBack>> = firestoreDataSource.getPersonalArchievements(uuid)
}
