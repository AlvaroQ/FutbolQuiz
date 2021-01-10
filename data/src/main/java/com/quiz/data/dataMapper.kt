package com.quiz.data

import com.quiz.data.models.ArchievementsBack
import com.quiz.domain.Archievements

fun ArchievementsBack.toArchievements(click:()->Unit): Archievements {
    return Archievements(
        userUid = userUid,
        typeChampionship = typeChampionship,
        typeGame = typeGame,
        points = points,
        createdAt = createdAt,
        click = click
    )
}