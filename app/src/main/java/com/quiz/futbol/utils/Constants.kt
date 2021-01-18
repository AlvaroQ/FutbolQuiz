package com.quiz.futbol.utils

object Constants {
    const val COLLECTION_USERS = "users"
    const val COLLECTION_FOLLOWERS = "followers"
    const val COLLECTION_FOLLOWING = "following"
    const val COLLECTION_ARCHIEVEMENTS = "archievement"

    const val COUNTER_DOWN_DEFAULT = 20000
    const val MIN_HITS_TO_UNLOCKED = 4

    const val POINTS = "points"
    const val STAGE_COMPLETED = "STAGE_COMPLETED"
    const val TOTAL_TEAMS_SPAIN_FIRST_DIVISION = 19
    const val TOTAL_TEAMS_ENGLAND_FIRST_DIVISION = 19
    const val TOTAL_TEAMS_ITALY_FIRST_DIVISION = 19

    const val PATH_REFERENCE_TEAMS = "stadiums/"
    const val PATH_REFERENCE_SPAIN = "spain/"
    const val PATH_REFERENCE_ITALY = "italy/"
    const val PATH_REFERENCE_ENGLAND = "england/"
    const val PATH_REFERENCE_FIRST_DIVISION = "first_division/"
    const val PATH_REFERENCE_APPS = "country/apps"

    enum class FollowTypes { FOLLOWING, FOLLOWER }
    enum class ModeGame { CARRER, TRAINNIG }
    enum class TypeGame { BY_NAME, BY_IMAGE, BY_CAPACITY, BY_BUILT }
    enum class TypeChampionship {
        SPAIN_FIRST_DIVISION,
        ENGLAND_FIRST_DIVISION,
        ITALY_FIRST_DIVISION,
        GERMAIN_FIRST_DIVISION,
        FRENCH_FIRST_DIVISION
    }
}