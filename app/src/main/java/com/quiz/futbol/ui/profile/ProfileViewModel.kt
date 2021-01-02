package com.quiz.futbol.ui.profile

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import com.quiz.data.toArchievements
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
                       private val getPersonalArchievements: GetPersonalArchievements,
                       private val getIsFollowingThisUser: GetIsFollowingThisUser
)  : ScopedViewModel() {
    private lateinit var user: User

    private val _userData = MutableLiveData<UiModel>()
    val userData: LiveData<UiModel> = _userData

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    init {
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_PROFILE)
    }

    fun loadUserPersonalData(userUuid: String?) {
        launch {
            val uuid: String = userUuid ?: uuid.invoke()
            when (val userResult = getUser.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR")
                is Either.Right -> {
                    user = userResult.b
                    _userData.value = if(userUuid == null) UiModel.MyPersonalData(user) else UiModel.FriendPersonalData(user)
                }
            }
            loadLevelUser(uuid)
            loadCountFollowingUser(uuid)
            loadCountFollowersUser(uuid)
            loadStageCompletedUserItems(uuid)
        }
    }

    fun isFollowed(friendUuid: String) {
        launch {
            when(val isFollowed = getIsFollowingThisUser.invoke(uuid.invoke(), friendUuid)) {
                is Either.Left -> log(TAG, "ERROR loading numberLevel")
                is Either.Right -> _userData.value = UiModel.IsFollowed(isFollowed.b)
            }
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
            val archievementResultList = mutableListOf<Archievements>()
            when(val mainArchievements = getGlobalArchievements.invoke()) {
                is Either.Left -> log(TAG, "ERROR loading Main Archievements")
                is Either.Right -> {
                    for (globalArchievement in mainArchievements.b) {
                        when (val userResult = getUser.invoke(globalArchievement.userUid!!)) {
                            is Either.Left -> log(TAG, "ERROR")
                            is Either.Right -> {
                                globalArchievement.displayName = userResult.b.displayName
                                globalArchievement.photoBase64 = userResult.b.photoBase64
                                archievementResultList.add(globalArchievement.toArchievements {
                                    // if user not me navigate to user selected
                                    if (uuid.invoke() != globalArchievement.userUid) navigationToProfile(userResult.b.uuid)
                                })
                            }
                        }
                    }
                    _userData.value = UiModel.MainArchievements(archievementResultList)
                }
            }
        }
    }

    fun loadPersonalArchievementsItems(userUuid: String?) {
        val archievementResultList = mutableListOf<Archievements>()
        launch {
            val uuid: String = userUuid ?: uuid.invoke()
            when(val personalArchievements = getPersonalArchievements.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR loading Main Archievements")
                is Either.Right -> {
                    for (personalArchievement in personalArchievements.b) {
                        when (val userResult = getUser.invoke(personalArchievement.userUid!!)) {
                            is Either.Left -> log(TAG, "ERROR")
                            is Either.Right -> {
                                personalArchievement.displayName = userResult.b.displayName
                                personalArchievement.photoBase64 = userResult.b.photoBase64
                                archievementResultList.add(personalArchievement.toArchievements {})
                            }
                        }
                    }
                    _userData.value = UiModel.PersonalArchievements(archievementResultList)
                }
            }
        }
    }

    fun navigationToProfile(uuid: String?) {
        _navigation.value = Navigation.FriendProfile(uuid)
    }

    fun goToEditProfile() {
        _navigation.value = Navigation.EditProfile
    }

    fun goToFollows(screenFollow: String) {
        _navigation.value = Navigation.Follows(screenFollow)
    }

    fun onUserImageClicked(imageView: ImageView) {
        _navigation.value = Navigation.Expand(imageView, user.photoBase64!!)
    }

    sealed class UiModel {
        data class MyPersonalData(val user: User) : UiModel()
        data class FriendPersonalData(val user: User) : UiModel()
        data class IsFollowed(val isFollowed: Boolean) : UiModel()
        data class Level(val numberLevel: Int) : UiModel()
        data class Followers(val numberFollowers: Int) : UiModel()
        data class Following(val numberFollowing: Int) : UiModel()
        data class MainUserStageCompleted(val items: MutableList<String>) : UiModel()
        data class MainArchievements(val items: MutableList<Archievements>) : UiModel()
        data class PersonalArchievements(val items: MutableList<Archievements>) : UiModel()
    }

    sealed class Navigation {
        object EditProfile : Navigation()
        data class Follows(val screenFollow: String) : Navigation()
        data class Expand(val imageView: ImageView, val icon: String): Navigation()
        data class FriendProfile(val uuid: String?): Navigation()
    }

    companion object {
        private val TAG = ProfileViewModel::class.java.simpleName
    }
}