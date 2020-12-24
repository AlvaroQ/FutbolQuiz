package com.quiz.domain

import java.io.Serializable

data class User(
    var uuid: String? = "",
    var displayName: String? = "",
    var displayDescription: String? = "",
    var displayLocation: String? = "",
    var email: String? = "",
    var phoneNumber: String? = "",
    var photoBase64: String? = "",
    var signInTimestamp: Long = System.currentTimeMillis(),
    var progressUser: Int = 0) : Serializable