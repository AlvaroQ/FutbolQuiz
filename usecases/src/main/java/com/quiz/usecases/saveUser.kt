package com.quiz.usecases

import arrow.core.Either
import com.quiz.data.repository.RepositoryException
import com.quiz.data.repository.UserRepository
import com.quiz.domain.User


class SaveUser(private val rankingRepository: UserRepository) {

    suspend fun invoke(user: User): Either<RepositoryException, User> = rankingRepository.signInUser(user)

}
