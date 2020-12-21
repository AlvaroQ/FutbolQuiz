package com.quiz.usecases

import arrow.core.Either
import com.quiz.data.repository.RepositoryException
import com.quiz.data.repository.UserRepository
import com.quiz.domain.User

class GetUser(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, User> = userRepository.getUserFromUuid(uuid)
}

class SaveUser(private val userRepository: UserRepository) {
    suspend fun invoke(user: User): Either<RepositoryException, User> = userRepository.signInUser(user)
}

class GetFollowers(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, Int> = userRepository.getFollowers(uuid)
}

class GetFollowing(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, Int> = userRepository.getFollowing(uuid)
}

class GetLevel(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, Int> = userRepository.getLevel(uuid)
}

class GetMainArchievements(private val userRepository: UserRepository) {
    suspend fun invoke(uuid: String): Either<RepositoryException, MutableList<String>> = userRepository.getMainArchievements(uuid)
}

