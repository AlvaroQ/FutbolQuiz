package com.quiz.futbol.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quiz.domain.Stadium
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.futbol.utils.Constants.PATH_REFERENCE_ENGLAND
import com.quiz.futbol.utils.Constants.PATH_REFERENCE_ITALY
import com.quiz.futbol.utils.Constants.PATH_REFERENCE_SPAIN
import com.quiz.futbol.utils.Constants.TypeChampionship
import com.quiz.futbol.utils.Constants.TOTAL_TEAMS_SPAIN_FIRST_DIVISION
import com.quiz.futbol.utils.Constants.TOTAL_TEAMS_ENGLAND_FIRST_DIVISION
import com.quiz.futbol.utils.Constants.TOTAL_TEAMS_ITALY_FIRST_DIVISION
import com.quiz.usecases.GetStadiumById
import com.quiz.usecases.GetTimestampGame
import com.quiz.usecases.SetTimestampGame

import kotlinx.coroutines.launch

class GameViewModel(private val getStadiumById: GetStadiumById,
                    val setTimestampGame: SetTimestampGame,
                    val getTimestampGame: GetTimestampGame) : ScopedViewModel() {
    private var randomCountries = mutableListOf<Int>()
    private lateinit var stadium: Stadium

    private val _question = MutableLiveData<Stadium>()
    val question: LiveData<Stadium> = _question

    private val _responseOptions = MutableLiveData<MutableList<Stadium>>()
    val responseOptions: LiveData<MutableList<Stadium>> = _responseOptions

    private val _progress = MutableLiveData<UiModel>()
    val progress: LiveData<UiModel> = _progress

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    init { AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_GAME) }

    fun generateNewStage(typeChampionship: String) {
        launch {
            _progress.value = UiModel.Loading(true)

            /** Generate question */
            val totalTeams = when(typeChampionship) {
                TypeChampionship.SPAIN_FIRST_DIVISION.name -> TOTAL_TEAMS_SPAIN_FIRST_DIVISION
                TypeChampionship.ENGLAND_FIRST_DIVISION.name -> TOTAL_TEAMS_ENGLAND_FIRST_DIVISION
                TypeChampionship.ITALY_FIRST_DIVISION.name -> TOTAL_TEAMS_ITALY_FIRST_DIVISION
                else -> TOTAL_TEAMS_SPAIN_FIRST_DIVISION
            }
            val numRandomMain = generateRandomWithExcusion(0, totalTeams, *randomCountries.toIntArray())
            randomCountries.add(numRandomMain)

            stadium = getStadium(numRandomMain, typeChampionship)

            /** Generate responses */
            val numRandomMainPosition = generateRandomWithExcusion(0, 3)

            val numRandomOption1 = generateRandomWithExcusion(1, TOTAL_TEAMS_SPAIN_FIRST_DIVISION, numRandomMain)
            val option1: Stadium = getStadium(numRandomOption1, typeChampionship)
            val numRandomPosition1 = generateRandomWithExcusion(0, 3, numRandomMainPosition)

            val numRandomOption2 = generateRandomWithExcusion(1, TOTAL_TEAMS_SPAIN_FIRST_DIVISION, numRandomMain, numRandomOption1)
            val option2: Stadium = getStadium(numRandomOption2, typeChampionship)
            val numRandomPosition2 = generateRandomWithExcusion(0, 3, numRandomMainPosition, numRandomPosition1)

            val numRandomOption3 = generateRandomWithExcusion(1, TOTAL_TEAMS_SPAIN_FIRST_DIVISION, numRandomMain, numRandomOption1, numRandomOption2)
            val option3: Stadium = getStadium(numRandomOption3, typeChampionship)
            val numRandomPosition3 = generateRandomWithExcusion(0, 3, numRandomMainPosition, numRandomPosition1, numRandomPosition2)

            /** Save value */
            val optionList: MutableList<Stadium> = mutableListOf(Stadium(), Stadium(), Stadium(), Stadium())
            optionList[numRandomMainPosition] = stadium
            optionList[numRandomPosition1] = option1
            optionList[numRandomPosition2] = option2
            optionList[numRandomPosition3] = option3

            _responseOptions.value = optionList
            _question.value = stadium
            _progress.value = UiModel.Loading(false)
        }
    }

    private suspend fun getStadium(id: Int, typeChampionship: String): Stadium {
        val championship = when(typeChampionship) {
            TypeChampionship.SPAIN_FIRST_DIVISION.name -> PATH_REFERENCE_SPAIN
            TypeChampionship.ENGLAND_FIRST_DIVISION.name -> PATH_REFERENCE_ENGLAND
            TypeChampionship.ITALY_FIRST_DIVISION.name -> PATH_REFERENCE_ITALY
            else -> PATH_REFERENCE_SPAIN
        }
        val stadium = getStadiumById.invoke(id, championship)
        stadium.id = id
        return stadium
    }

    fun navigateToResult(points: String) {
        AnalyticsManager.analyticsGameFinished(points)
        _navigation.value = Navigation.Result
    }

    fun getStadium() : Stadium? {
        return if(this::stadium.isInitialized) stadium
        else null
    }

    private fun generateRandomWithExcusion(start: Int, end: Int, vararg exclude: Int): Int {
        var numRandom = (start..end).random()
        while(exclude.contains(numRandom)){
            numRandom = (start..end).random()
        }
        return numRandom
    }

    fun getVerificationSent(): Long {
        return getTimestampGame()
    }

    fun setVerificationSent(time: Long) {
        setTimestampGame(time)
    }

    sealed class UiModel {
        data class Loading(val show: Boolean) : UiModel()
    }

    sealed class Navigation {
        object Result : Navigation()
    }
}