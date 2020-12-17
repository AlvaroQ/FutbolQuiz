package com.quiz.domain

import java.io.Serializable

data class User(
    var uuid: String? = "",
    var displayName: String? = "",
    var email: String? = "",
    var phoneNumber: String? = "",
    var photoUrl: String? = "") : Serializable