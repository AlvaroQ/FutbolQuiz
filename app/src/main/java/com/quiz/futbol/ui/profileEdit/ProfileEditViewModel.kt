package com.quiz.futbol.ui.profileEdit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import com.quiz.domain.User
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.futbol.utils.log
import com.quiz.usecases.GetUUID
import com.quiz.usecases.GetUser
import com.quiz.usecases.SaveUser
import kotlinx.coroutines.launch

class ProfileEditViewModel(private val myUuid: GetUUID,
                           private val getUser: GetUser,
                           private val saveUser: SaveUser
)  : ScopedViewModel() {

    private val mNav = MutableLiveData<ProfileNavigation>()
    val nav: LiveData<ProfileNavigation> = mNav

    private val _userData = MutableLiveData<UiModel>()
    val userData: LiveData<UiModel> = _userData

    lateinit var user: User

    init {
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_EDIT_PROFILE)
        loadUserPersonalData()
    }

    fun clickOnPicker() {
        mNav.value = ProfileNavigation.PickerNavigation
    }

    private fun loadUserPersonalData() {
        launch {
            val uuid = myUuid.invoke()
            when (val userResult = getUser.invoke(uuid)) {
                is Either.Left -> log(TAG, "ERROR")
                is Either.Right -> {
                    user = userResult.b
                    _userData.value = UiModel.UserPersonalData(userResult.b)
                }
            }
        }
    }

    fun updateUserData(name: String, description: String, location: String, imageBase64: String) {
        launch {
            user.apply {
                uuid = myUuid.invoke()
                displayName = name
                displayDescription = description
                displayLocation = location
                photoBase64 = imageBase64
            }
            saveUser.invoke(user)
            mNav.value = ProfileNavigation.FinishNavigation
        }
    }

    sealed class UiModel {
        data class UserPersonalData(val user: User) : UiModel()
    }

    sealed class ProfileNavigation {
        object PickerNavigation : ProfileNavigation()
        object FinishNavigation : ProfileNavigation()
    }

    companion object {
        private val TAG = ProfileEditViewModel::class.java.simpleName
    }
}