package com.quiz.usecases

import com.quiz.data.repository.SharedPreferencesRepository

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

class GetLevelLocal(private val sharedPreferencesRepository: SharedPreferencesRepository) {
    operator fun invoke() = sharedPreferencesRepository.levelLocal
}

class SetLevelLocal(private val sharedPreferencesRepository: SharedPreferencesRepository) {
    operator fun invoke(level: Int) {
        sharedPreferencesRepository.levelLocal = level
    }
}

class GetTimestampGame(private val sharedPreferencesRepository: SharedPreferencesRepository) {
    operator fun invoke() = sharedPreferencesRepository.timestampGame
}

class SetTimestampGame(private val sharedPreferencesRepository: SharedPreferencesRepository) {
    operator fun invoke(value: Long) {
        sharedPreferencesRepository.timestampGame = value
    }
}