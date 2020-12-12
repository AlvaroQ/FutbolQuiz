package com.quiz.futbol.utils

object Constants {
    const val POINTS = "points"
    const val RECORD_PERSONAL = "personalRecord"
    const val TOTAL_COUNTRIES = 20
    const val PATH_REFERENCE_COUNTRIES = "teams/spain/first_division"
    const val PATH_REFERENCE_APPS = "country/apps"
    const val COLLECTION_RANKING = "ranking-teams"

    const val TYPE_GAME = "TypeGame"
    enum class TypeGame { BY_NAME, BY_IMAGE, BY_CAPACITY, BY_BUILT }
    enum class TypeChampionship {
        SPAIN_FIRST_DIVISION, SPAIN_SECOND_DIVISION,
        ENGLAND_FIRST_DIVISION, ENGLAND_SECOND_DIVISION,
        ITALY_FIRST_DIVISION, ITALY_SECOND_DIVISION}
}