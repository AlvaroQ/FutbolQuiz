package com.quiz.usecases

import com.quiz.data.repository.StadiumByIdRepository
import com.quiz.domain.Stadium

class GetStadiumById(private val countryByIdRepository: StadiumByIdRepository) {

    suspend fun invoke(id: Int): Stadium = countryByIdRepository.getStadiumById(id)

}
