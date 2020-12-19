package com.quiz.usecases

import arrow.core.Either
import com.quiz.data.repository.RepositoryException
import com.quiz.data.repository.UserRepository
import com.quiz.domain.User

class GetUser(private val rankingRepository: UserRepository) {

    suspend fun invoke(uuid: String): Either<RepositoryException, User> = rankingRepository.getUserFromUuid(uuid)

}