package com.quiz.data.models

data class ArchievementsBack(
        var userUid: String? = "",
        var typeChampionship: String? = "",
        var typeGame: String? = "",
        var points: Long = 0,
        var createdAt: Long = System.currentTimeMillis()
)