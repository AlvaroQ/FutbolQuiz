package com.quiz.futbol.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quiz.domain.Stadium
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.futbol.utils.Constants.TOTAL_TEAMS_SPAIN_FIRST_DIVISION
import com.quiz.usecases.GetStadiumById
import kotlinx.coroutines.launch

class GameViewModel(private val getStadiumById: GetStadiumById) : ScopedViewModel() {
    private var randomCountries = mutableListOf<Int>()
    private lateinit var stadium: Stadium

    private val _question = MutableLiveData<Stadium>()
    val question: LiveData<Stadium> = _question

    private val _responseOptions = MutableLiveData<MutableList<String>>()
    val responseOptions: LiveData<MutableList<String>> = _responseOptions

    private val _progress = MutableLiveData<UiModel>()
    val progress: LiveData<UiModel> = _progress

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    init {
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_GAME)
        generateNewStage()
    }

    fun generateNewStage() {
        launch {
            _progress.value = UiModel.Loading(true)

            /** Generate question */
            val numRandomMain = generateRandomWithExcusion(0, TOTAL_TEAMS_SPAIN_FIRST_DIVISION, *randomCountries.toIntArray())
            randomCountries.add(numRandomMain)

            stadium = getStadium(numRandomMain)

            /** Generate responses */
            val numRandomMainPosition = generateRandomWithExcusion(0, 3)

            val numRandomOption1 = generateRandomWithExcusion(1, TOTAL_TEAMS_SPAIN_FIRST_DIVISION, numRandomMain)
            val option1: Stadium = getStadium(numRandomOption1)
            val numRandomPosition1 = generateRandomWithExcusion(0, 3, numRandomMainPosition)

            val numRandomOption2 = generateRandomWithExcusion(1, TOTAL_TEAMS_SPAIN_FIRST_DIVISION, numRandomMain, numRandomOption1)
            val option2: Stadium = getStadium(numRandomOption2)
            val numRandomPosition2 = generateRandomWithExcusion(0, 3, numRandomMainPosition, numRandomPosition1)

            val numRandomOption3 = generateRandomWithExcusion(1, TOTAL_TEAMS_SPAIN_FIRST_DIVISION, numRandomMain, numRandomOption1, numRandomOption2)
            val option3: Stadium = getStadium(numRandomOption3)
            val numRandomPosition3 = generateRandomWithExcusion(0, 3, numRandomMainPosition, numRandomPosition1, numRandomPosition2)

            /** Save value */
            val optionList = mutableListOf("", "", "", "")
            optionList[numRandomMainPosition] = stadium.name!!
            optionList[numRandomPosition1] = option1.name!!
            optionList[numRandomPosition2] = option2.name!!
            optionList[numRandomPosition3] = option3.name!!

            _responseOptions.value = optionList
            _question.value = stadium
            _progress.value = UiModel.Loading(false)
        }
    }

    private suspend fun getStadium(id: Int): Stadium {
        return getStadiumById.invoke(id)
    }

    fun navigateToResult(points: String) {
        AnalyticsManager.analyticsGameFinished(points)
        _navigation.value = Navigation.Result
    }

    fun getStadium() : Stadium? {
        return stadium
    }

    private fun generateRandomWithExcusion(start: Int, end: Int, vararg exclude: Int): Int {
        var numRandom = (start..end).random()
        while(exclude.contains(numRandom)){
            numRandom = (start..end).random()
        }
        return numRandom
    }

    sealed class UiModel {
        data class Loading(val show: Boolean) : UiModel()
    }

    sealed class Navigation {
        object Result : Navigation()
    }
}