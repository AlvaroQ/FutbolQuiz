package com.quiz.futbol.ui.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.Analytics

class SelectViewModel : ScopedViewModel() {

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    init {
        Analytics.analyticsScreenViewed(Analytics.SCREEN_SELECT_GAME)
    }

    fun navigateToGameByFlag() {
        _navigation.value = Navigation.GameByImage
    }


    fun navigateToGameByCountry() {
        _navigation.value = Navigation.GameByName
    }

    sealed class Navigation {
        object GameByImage : Navigation()
        object GameByName : Navigation()
    }
}