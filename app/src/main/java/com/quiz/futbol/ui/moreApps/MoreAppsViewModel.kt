package com.quiz.futbol.ui.moreApps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quiz.domain.App
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.usecases.GetAppsRecommended
import kotlinx.coroutines.launch

class MoreAppsViewModel(private val getAppsRecommended: GetAppsRecommended) : ScopedViewModel() {

    private val _progress = MutableLiveData<UiModel>()
    val progress: LiveData<UiModel> = _progress

    private val _list = MutableLiveData<MutableList<App>>()
    val list: LiveData<MutableList<App>> = _list

    init {
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_MORE_APPS)
        launch {
            _progress.value = UiModel.Loading(true)
            _list.value = appsRecommended()
            _progress.value = UiModel.Loading(false)
        }
    }

    private suspend fun appsRecommended(): MutableList<App> {
        return getAppsRecommended.invoke()
    }

    sealed class UiModel {
        data class Loading(val show: Boolean) : UiModel()
    }
}