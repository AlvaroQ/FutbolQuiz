package com.quiz.futbol.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import arrow.core.right
import com.quiz.data.models.ArchievementsBack
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.futbol.ui.follows.FollowsViewModel
import com.quiz.futbol.ui.profile.ProfileViewModel
import com.quiz.futbol.utils.log
import com.quiz.usecases.*
import kotlinx.coroutines.launch

class ResultViewModel(private val getPaymentDone: GetPaymentDone,
                      private val uuid: GetUUID,
                      private val getLevelLocal: GetLevelLocal,
                      private val setLevelLocal: SetLevelLocal,
                      private val setUserLevel: SetUserLevel,
                      private val setUserStageCompleted: SetUserStageCompleted) : ScopedViewModel() {

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private val _showingAds = MutableLiveData<UiModel>()
    val showingAds: LiveData<UiModel> = _showingAds

    init {
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_RESULT)
        launch {
            //_showingAds.value = UiModel.ShowAd(!getPaymentDone())
        }
    }

    fun uploadArchievement(archievement: ArchievementsBack) {
        archievement.userUid = uuid.invoke()
        launch {
            setUserStageCompleted.invoke(archievement)
        }
    }

    fun upgradeUserLevel() {
        launch {
            val level = getLevelLocal.invoke() + 1
            when(val newLevel = setUserLevel.invoke(uuid.invoke(), level)) {
                is Either.Right -> setLevelLocal.invoke(newLevel.b)
                is Either.Left -> log(TAG, "ERROR upgradeUserLevel")
            }

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
        data class NewArchievement(val archievementNumber: Int) : Navigation()
    }

    sealed class UiModel {
        data class ShowAd(val show: Boolean) : UiModel()
    }

    companion object {
        private val TAG = ResultViewModel::class.java.simpleName
    }
}