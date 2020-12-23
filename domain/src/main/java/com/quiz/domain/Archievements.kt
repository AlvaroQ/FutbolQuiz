package com.quiz.domain

data class Archievements(
        var userUid: String? = "",
        var displayName: String? = "",
        var photoBase64: String? = "",
        var typeChampionship: String? = "",
        var typeGame: String? = "",
        var points: Long = 0,
        var createdAt: Long)