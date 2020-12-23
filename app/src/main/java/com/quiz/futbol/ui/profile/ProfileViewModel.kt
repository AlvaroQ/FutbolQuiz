package com.quiz.futbol.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import com.quiz.domain.Archievements
import com.quiz.domain.User
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.futbol.utils.log
import com.quiz.usecases.*
import kotlinx.coroutines.launch

class ProfileViewModel(private val uuid: GetUUID,
                       private val getUser: GetUser,
                       private val getUserLevel: GetUserLevel,
                       private val getCountFollowers: GetCountFollowers,
                       private val getCountFollowing: GetCountFollowing,
                       private val getUserStageCompleted: GetUserStageCompleted,
                       private val getGlobalArchievements: GetGlobalArchievements,
                       private val getPersonalArchievements: GetPersonalArchievements
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
            loadCountFollowingUser(uuid)
            loadCountFollowersUser(uuid)
            loadStageCompletedUserItems(uuid)
        }
    }

    private fun loadLevelUser(uuid: String) {
        launch {
            when(val numberLevel = getUserLevel.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR loading numberLevel")
                is Either.Right -> _userData.value = UiModel.Level(numberLevel.b)
            }
        }
    }

    private fun loadCountFollowingUser(uuid: String) {
        launch {
            when(val numberFollowing = getCountFollowing.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR loading count Following")
                is Either.Right -> _userData.value = UiModel.Following(numberFollowing.b)
            }
        }
    }

    private fun loadCountFollowersUser(uuid: String) {
        launch {
            when(val numberFollowers = getCountFollowers.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR loading count Followers")
                is Either.Right -> _userData.value = UiModel.Followers(numberFollowers.b)
            }
        }
    }

    private fun loadStageCompletedUserItems(uuid: String) {
        launch {
            when(val userStageCompleted = getUserStageCompleted.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR loading Count Main Archievements")
                is Either.Right -> _userData.value = UiModel.MainUserStageCompleted(userStageCompleted.b)
            }
        }
    }

    fun loadGlobalArchievementsItems() {
        launch {
            when(val mainArchievements = getGlobalArchievements.invoke()) {
                is Either.Left -> log(TAG, "ERROR loading Main Archievements")
                is Either.Right -> _userData.value = UiModel.MainArchievements(mainArchievements.b)
            }
        }
    }

    fun loadPersonalArchievementsItems() {
        launch {
            val uuid = uuid.invoke()
            when(val personalArchievements = getPersonalArchievements.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR loading Main Archievements")
                is Either.Right -> _userData.value = UiModel.PersonalArchievements(personalArchievements.b)
            }
        }
    }

    sealed class UiModel {
        data class UserPersonalData(val user: User) : UiModel()
        data class Level(val numberLevel: Int) : UiModel()
        data class Followers(val numberFollowers: Int) : UiModel()
        data class Following(val numberFollowing: Int) : UiModel()
        data class MainUserStageCompleted(val items: MutableList<String>) : UiModel()
        data class MainArchievements(val items: MutableList<Archievements>) : UiModel()
        data class PersonalArchievements(val items: MutableList<Archievements>) : UiModel()
    }

    companion object {
        private val TAG = ProfileViewModel::class.java.simpleName
    }
}