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
import com.quiz.usecases.*
import kotlinx.coroutines.launch

class ProfileViewModel(private val uuid: GetUUID,
                       private val getUser: GetUser,
                       private val getLevel: GetLevel,
                       private val getFollowers: GetFollowers,
                       private val getFollowing: GetFollowing,
                       private val getMainArchievements: GetMainArchievements,
)  : ScopedViewModel() {


    private val _userData = MutableLiveData<UiModel>()
    val userData: LiveData<UiModel> = _userData

    init {
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_PROFILE)
        loadUserPersonalData()
    }

    private fun loadUserPersonalData() {
        launch {
            val uuid = uuid.invoke()
            when (val userResult = getUser.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR")
                is Either.Right -> _userData.value = UiModel.UserPersonalData(userResult.b)
            }
            loadLevelUser(uuid)
            loadFollowingUser(uuid)
            loadFollowersUser(uuid)
            loadMainArchievementsItems(uuid)
        }
    }

    private fun loadLevelUser(uuid: String) {
        launch {
            when(val numberLevel = getLevel.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR loading numberLevel")
                is Either.Right -> _userData.value = UiModel.Level(numberLevel.b)
            }
        }
    }

    private fun loadFollowingUser(uuid: String) {
        launch {
            when(val numberFollowing = getFollowing.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR loading Following")
                is Either.Right -> _userData.value = UiModel.Following(numberFollowing.b)
            }
        }
    }

    private fun loadFollowersUser(uuid: String) {
        launch {
            when(val numberFollowers = getFollowers.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR loading Followers")
                is Either.Right -> _userData.value = UiModel.Followers(numberFollowers.b)
            }
        }
    }

    private fun loadMainArchievementsItems(uuid: String) {
        launch {
            when(val mainArchievements = getMainArchievements.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR loading Main Archievements")
                is Either.Right -> _userData.value = UiModel.MainArchievements(mainArchievements.b)
            }
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