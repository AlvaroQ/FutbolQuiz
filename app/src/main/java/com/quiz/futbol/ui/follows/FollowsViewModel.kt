package com.quiz.futbol.ui.follows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import arrow.core.right
import com.quiz.domain.User
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.futbol.ui.profile.ProfileViewModel
import com.quiz.futbol.utils.log
import com.quiz.usecases.*
import kotlinx.coroutines.launch

class FollowsViewModel(private val uuid: GetUUID,
                       private val getUser: GetUser,
                       private val setUnfollower: SetUnfollower,
                       private val setUnfollowing: SetUnfollowing,
                       private val getFollowing: GetFollowing,
                       private val getFollowers: GetFollowers) : ScopedViewModel() {

    private val _followsData = MutableLiveData<UiModel>()
    val followsData: LiveData<UiModel> = _followsData

    private val _unfollowFinished = MutableLiveData<UiModel>()
    val unfollowFinished: LiveData<UiModel> = _unfollowFinished

    init {
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_FOLLOWS)
    }

    fun loadFollowingList() {
        launch {
            val userResultList = mutableListOf<User>()
            when(val getFollowing = getFollowing.invoke(uuid.invoke())) {
                is Either.Left -> log(TAG, "ERROR loading Following")
                is Either.Right -> {
                    for (user in getFollowing.b) {
                        when (val userResult = getUser.invoke(user.uuid!!)) {
                            is Either.Left -> log(TAG, "ERROR")
                            is Either.Right -> userResultList.add(userResult.b)
                        }
                    }
                    _followsData.value = UiModel.FilledUserList(userResultList)
                }
            }
        }
    }

    fun loadFollowersList() {
        launch {
            val userResultList = mutableListOf<User>()
            when(val getFollowers = getFollowers.invoke(uuid.invoke())) {
                is Either.Left -> log(TAG, "ERROR loading Following")
                is Either.Right -> {
                    for (user in getFollowers.b) {
                        when (val userResult = getUser.invoke(user.uuid!!)) {
                            is Either.Left -> log(TAG, "ERROR")
                            is Either.Right -> userResultList.add(userResult.b)
                        }
                    }
                    _followsData.value = UiModel.FilledUserList(userResultList)
                }
            }
        }
    }

    fun unfollow(uuidToUnfollow: String) {
        launch {
            when(setUnfollower.invoke(uuid.invoke(), uuidToUnfollow)) {
                is Either.Right -> _unfollowFinished.value = UiModel.FollowerResult(true)
                else -> _unfollowFinished.value = UiModel.FollowerResult(false)
            }
            when(setUnfollowing.invoke(uuidToUnfollow, uuid.invoke())) {
                is Either.Right -> _unfollowFinished.value = UiModel.FollowingResult(true)
                else -> _unfollowFinished.value = UiModel.FollowingResult(false)
            }
        }
    }

    sealed class UiModel {
        data class FilledUserList(val userList: MutableList<User>) : UiModel()
        data class FollowerResult(val isSuccess: Boolean) : UiModel()
        data class FollowingResult(val isSuccess: Boolean) : UiModel()
    }

    companion object {
        private val TAG = ProfileViewModel::class.java.simpleName
    }
}