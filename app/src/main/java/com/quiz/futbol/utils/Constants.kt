package com.quiz.futbol.utils

object Constants {
    const val POINTS = "points"
    const val TOTAL_TEAMS_SPAIN_FIRST_DIVISION = 19
    const val PATH_REFERENCE_TEAMS = "stadiums/"
    const val PATH_REFERENCE_SPAIN = "spain/"
    const val PATH_REFERENCE_FIRST_DIVISION = "first_division/"
    const val PATH_REFERENCE_APPS = "country/apps"
    const val COLLECTION_RANKING = "ranking-teams"

    const val TYPE_GAME = "TypeGame"
    const val TYPE_CHAMPIONSHIP = "TypeChampionship"
    enum class ModeGame { CARRER, TRAINIG }
    enum class TypeGame { BY_NAME, BY_IMAGE, BY_CAPACITY, BY_BUILT }
    enum class TypeChampionship {
        SPAIN_FIRST_DIVISION,
        ENGLAND_FIRST_DIVISION,
        ITALY_FIRST_DIVISION,
        GERMAIN_FIRST_DIVISION,
        FRENCH_FIRST_DIVISION
    }
}