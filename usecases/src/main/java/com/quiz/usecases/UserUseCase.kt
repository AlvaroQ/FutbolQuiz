package com.quiz.usecases

import arrow.core.Either
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

class GetFollowers(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, MutableList<User>> = userRepository.getFollowers(uuid)
}

class GetCountFollowing(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, Int> = userRepository.getCountFollowing(uuid)
}
class GetFollowing(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, MutableList<User>> = userRepository.getFollowing(uuid)
}

class GetUserLevel(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, Int> = userRepository.getUserLevel(uuid)
}

class GetUserStageCompleted(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, MutableList<String>> = userRepository.getUserStageCompleted(uuid)
}

class GetGlobalArchievements(private val userRepository: UserRepository) {
    suspend fun invoke(): Either<RepositoryException, MutableList<Archievements>> = userRepository.getGlobalArchievements()
}

class GetPersonalArchievements(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, MutableList<Archievements>> = userRepository.getPersonalArchievements(uuid)
}

