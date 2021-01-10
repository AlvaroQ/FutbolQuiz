package com.quiz.usecases

import arrow.core.Either
import com.quiz.data.models.ArchievementsBack
import com.quiz.data.repository.RepositoryException
import com.quiz.data.repository.UserRepository
import com.quiz.domain.Archievements
import com.quiz.domain.User

class GetUser(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, User> = userRepository.getUserFromUuid(uuid)
}

class SaveUser(private val userRepository: UserRepository) {
    suspend fun invoke(user: User): Either<RepositoryException, User> = userRepository.signInUser(user)
}

class GetCountFollowers(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, Int> = userRepository.getCountFollowers(uuid)
}

class SetUnfollower(private val userRepository: UserRepository) {
    suspend fun invoke(fromUuid: String, toUuid: String): Either<RepositoryException, Boolean> = userRepository.setUnfollower(fromUuid, toUuid)
}

class SetUnfollowing(private val userRepository: UserRepository) {
    suspend fun invoke(fromUuid: String, toUuid: String): Either<RepositoryException, Boolean> = userRepository.setUnfollowing(fromUuid, toUuid)
}

class SetFollower(private val userRepository: UserRepository) {
    suspend fun invoke(fromUuid: String, toUuid: String): Either<RepositoryException, Boolean> = userRepository.setFollower(fromUuid, toUuid)
}


class SetFollowing(private val userRepository: UserRepository) {
    suspend fun invoke(fromUuid: String, toUuid: String): Either<RepositoryException, Boolean> = userRepository.setFollowing(fromUuid, toUuid)
}

class GetFollowers(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, MutableList<User>> = userRepository.getFollowers(uuid)
}

class GetCountFollowing(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, Int> = userRepository.getCountFollowing(uuid)
}

class GetFollowing(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, MutableList<User>> = userRepository.getFollowing(uuid)
}
class GetIsFollowingThisUser(private val userRepository: UserRepository) {
    suspend fun invoke(myUuid: String, uuid: String): Either<RepositoryException, Boolean> = userRepository.getIsFollowingThisUser(myUuid, uuid)
}

class GetUserLevel(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, Int> = userRepository.getUserLevel(uuid)
}

class SetUserLevel(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String, level: Int): Either<RepositoryException, Int> = userRepository.setUserLevel(uuid, level)
}

class GetUserStageCompleted(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, MutableList<String>> = userRepository.getUserStageCompleted(uuid)
}

class SetUserStageCompleted(private val userRepository: UserRepository) {
    suspend fun invoke(archievementBack: ArchievementsBack): Either<RepositoryException, Boolean> = userRepository.setUserStageCompleted(archievementBack)
}

class GetGlobalArchievements(private val userRepository: UserRepository) {
    suspend fun invoke(): Either<RepositoryException, MutableList<ArchievementsBack>> = userRepository.getGlobalArchievements()
}

class GetPersonalArchievements(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, MutableList<ArchievementsBack>> = userRepository.getPersonalArchievements(uuid)
}

