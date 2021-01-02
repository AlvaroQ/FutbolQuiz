package com.quiz.data

import com.quiz.data.models.ArchievementsBack
import com.quiz.domain.Archievements

fun ArchievementsBack.toArchievements(click:()->Unit): Archievements {
    return Archievements(
            userUid,
            displayName,
            photoBase64,
            typeChampionship,
            typeGame,
            points,
            createdAt,
            click
    )
}