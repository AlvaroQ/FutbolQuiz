package com.quiz.futbol.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import com.quiz.domain.User
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.futbol.utils.Constants.SPAIN
import com.quiz.futbol.utils.Constants.ENGLAND
import com.quiz.futbol.utils.Constants.ITALY
import com.quiz.futbol.utils.Constants.GERMANY
import com.quiz.futbol.utils.Constants.FRANCE
import com.quiz.futbol.utils.Constants.BRAZIL
import com.quiz.futbol.utils.Constants.ARGENTINA
import com.quiz.futbol.utils.log
import com.quiz.usecases.GetUUID
import com.quiz.usecases.GetUser
import kotlinx.coroutines.launch

class ProfileViewModel(private val uuid: GetUUID,
                       private val getUser: GetUser
)  : ScopedViewModel() {


    private val _userData = MutableLiveData<UiModel>()
    val userData: LiveData<UiModel> = _userData

    init {
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_PROFILE)
        loadUserPersonalData()
        loadLevelUser()
        loadFollowingUser()
        loadFollowersUser()
        loadMainArchievementsItems()
    }

    private fun loadUserPersonalData() {
        launch {
            val uuid = uuid.invoke()
            when (val userResult = getUser.invoke(uuid)) {
                is Either.Left -> {
                    log(TAG, "ERROR")
                }
                is Either.Right -> {
                    _userData.value = UiModel.UserPersonalData(userResult.b)
                }
            }
        }
    }

    private fun loadLevelUser() {
        launch {
            _userData.value = UiModel.Level(6)
        }
    }

    private fun loadFollowingUser() {
        launch {
            _userData.value = UiModel.Following(11)
        }
    }

    private fun loadFollowersUser() {
        launch {
            _userData.value = UiModel.Followers(21)
        }
    }

    private fun loadMainArchievementsItems() {
        launch {
            _userData.value = UiModel.MainArchievements(
                    mutableListOf(SPAIN, ENGLAND, ITALY, GERMANY, FRANCE, BRAZIL, ARGENTINA))
        }
    }

    sealed class UiModel {
        data class UserPersonalData(val user: User) : UiModel()
        data class Level(val numberLevel: Int) : UiModel()
        data class Followers(val numberFollowers: Int) : UiModel()
        data class Following(val numberFollowing: Int) : UiModel()
        data class MainArchievements(val items: MutableList<String>) : UiModel()
    }

    companion object {
        private val TAG = ProfileViewModel::class.java.simpleName
    }
}