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

class SetCurrentUser(private val sharedPreferencesRepository: SharedPreferencesRepository) {
    operator fun invoke(value: User) {
        sharedPreferencesRepository.currentUser = value
    }
}

class GetCurrentUser(private val sharedPreferencesRepository: SharedPreferencesRepository) {
    operator fun invoke() = sharedPreferencesRepository.currentUser
}