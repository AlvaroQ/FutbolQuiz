package com.quiz.usecases

import com.quiz.data.repository.SharedPreferencesRepository
import com.quiz.domain.User

class SetPaymentDone(private val sharedPreferencesRepository: SharedPreferencesRepository) {
    operator fun invoke(value: Boolean) {
        sharedPreferencesRepository.paymentDone = value
    }
}

class GetPaymentDone(private val sharedPreferencesRepository: SharedPreferencesRepository) {
    operator fun invoke() = sharedPreferencesRepository.paymentDone
}

class SetUUID(private val sharedPreferencesRepository: SharedPreferencesRepository) {
    operator fun invoke(value: String) {
        sharedPreferencesRepository.uuid = value
    }
}

class GetUUID(private val sharedPreferencesRepository: SharedPreferencesRepository) {
    operator fun invoke() = sharedPreferencesRepository.uuid
}