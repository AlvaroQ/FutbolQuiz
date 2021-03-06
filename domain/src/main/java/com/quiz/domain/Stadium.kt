package com.quiz.domain

data class Stadium(
    var id: Int = 0,
    var name: String? = "",
    var shield: String? = "",
    var city: String? = "",
    var region: String? = "",
    var division: String? = "",
    var stadium_name: String? = "",
    var stadium_image: String? = "",
    var build: Int? = 1947,
    var capacity: Int? = 85454
)