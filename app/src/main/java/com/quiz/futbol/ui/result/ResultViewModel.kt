package com.quiz.futbol.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.usecases.GetPaymentDone
import kotlinx.coroutines.launch

class ResultViewModel(private val getPaymentDone: GetPaymentDone) : ScopedViewModel() {

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private val _showingAds = MutableLiveData<UiModel>()
    val showingAds: LiveData<UiModel> = _showingAds

    init {
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_RESULT)
        launch {
            _showingAds.value = UiModel.ShowAd(!getPaymentDone())
        }
    }

    fun navigateToGame() {
        AnalyticsManager.analyticsClicked(AnalyticsManager.BTN_PLAY_AGAIN)
        _navigation.value = Navigation.Game
    }

    fun navigateToRate() {
        AnalyticsManager.analyticsClicked(AnalyticsManager.BTN_RATE)
        _navigation.value = Navigation.Rate
    }

    fun navigateToShare(points: Int) {
        AnalyticsManager.analyticsClicked(AnalyticsManager.BTN_SHARE)
        _navigation.value = Navigation.Share(points)
    }

    sealed class Navigation {
        data class Share(val points: Int) : Navigation()
        object Rate : Navigation()
        object Game : Navigation()
        data class Open(val url : String): Navigation()
    }

    sealed class UiModel {
        data class ShowAd(val show: Boolean) : UiModel()
    }
}