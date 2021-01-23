package com.quiz.futbol.ui.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import com.quiz.domain.User
import com.quiz.futbol.R
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.futbol.utils.Constants.TypeGame
import com.quiz.futbol.utils.Constants.ModeGame
import com.quiz.futbol.utils.Constants.ModeGame.CARRER
import com.quiz.futbol.utils.Constants.ModeGame.TRAINNIG
import com.quiz.futbol.utils.Constants.TypeChampionship
import com.quiz.futbol.utils.Constants.TypeChampionship.*
import com.quiz.futbol.utils.Constants.TypeGame.*
import com.quiz.futbol.utils.GetResources
import com.quiz.futbol.utils.log
import com.quiz.usecases.GetUUID
import com.quiz.usecases.GetUser
import kotlinx.coroutines.launch

class SelectViewModel(private val getResources: GetResources,
                      private val uuid: GetUUID,
                      private val getUser: GetUser
) : ScopedViewModel() {
    var progressUser: Int = 0

    private val _userData = MutableLiveData<UiModel>()
    val userData: LiveData<UiModel> = _userData

    private val _loadBottomSheetData = MutableLiveData<UiModel>()
    val loadBottomSheetData: LiveData<UiModel> = _loadBottomSheetData

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private val _dialog = MutableLiveData<Dialog>()
    val dialog: LiveData<Dialog> = _dialog

    init {
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_SELECT_GAME)
        loadUserData()
    }

    fun loadTrainingMode() {
        _loadBottomSheetData.value = UiModel.ContentCareerMode(loadSelectAllItems(), TRAINNIG)
    }

    fun loadCareerMode() {
        _loadBottomSheetData.value = UiModel.ContentCareerMode(loadSelectAllItems(), CARRER)
    }

    private fun loadUserData() {
        launch {
            when (val userResult = getUser.invoke(uuid.invoke())) {
                is Either.Left -> log(TAG, "ERROR")
                is Either.Right -> {
                    progressUser = userResult.b.progressUser
                    _userData.value = UiModel.UserData(userResult.b)
                }
            }
        }
    }

    private fun loadSelectAllItems(): MutableList<SelectItem> {
        val listOf = mutableListOf<SelectItem>()
        listOf.addAll(loadSelectItems(SPAIN_FIRST_DIVISION))
        listOf.addAll(loadSelectItems(ENGLAND_FIRST_DIVISION))
        listOf.addAll(loadSelectItems(ITALY_FIRST_DIVISION))
        //listOf.addAll(loadSelectItems(GERMAIN_FIRST_DIVISION))
        //listOf.addAll(loadSelectItems(FRENCH_FIRST_DIVISION))
        return listOf
    }

    private fun loadSelectItems(typeChampionship: TypeChampionship): MutableList<SelectItem> {
        val listOf = mutableListOf<SelectItem>()

        val title = when(typeChampionship) {
            SPAIN_FIRST_DIVISION -> getResources.getString(R.string.spain_league)
            ENGLAND_FIRST_DIVISION -> getResources.getString(R.string.england_league)
            ITALY_FIRST_DIVISION -> getResources.getString(R.string.italy_league)
            GERMAIN_FIRST_DIVISION -> getResources.getString(R.string.german_league)
            FRENCH_FIRST_DIVISION -> getResources.getString(R.string.french_league)
        }
        val itemHeader = SelectItem(title, false, null, typeChampionship){}

        val itemSelectImageItem = SelectItem(getResources.getString(R.string.by_image), isBlocked(typeChampionship, BY_IMAGE), BY_IMAGE) {
            when {
                isBlocked(typeChampionship, BY_IMAGE) -> _dialog.value = Dialog.DialogLevelLock
                else -> _navigation.value = Navigation.GameByImage(typeChampionship)
            }
        }
        val itemSelectNameItem = SelectItem(getResources.getString(R.string.by_name), isBlocked(typeChampionship, BY_NAME), BY_NAME) {
            when {
                isBlocked(typeChampionship, BY_NAME) -> _dialog.value = Dialog.DialogLevelLock
                else -> _navigation.value = Navigation.GameByName(typeChampionship)
            }
        }
        val itemSelectShieldItem = SelectItem(getResources.getString(R.string.by_shield), isBlocked(typeChampionship, BY_SHIELD), BY_SHIELD) {
            when {
                isBlocked(typeChampionship, BY_SHIELD) -> _dialog.value = Dialog.DialogLevelLock
                else -> _navigation.value = Navigation.GameByShield(typeChampionship)
            }
        }
        val itemSelectCapacityItem = SelectItem(getResources.getString(R.string.by_capacity), isBlocked(typeChampionship, BY_CAPACITY), BY_CAPACITY) {
            when {
                isBlocked(typeChampionship, BY_CAPACITY) -> _dialog.value = Dialog.DialogLevelLock
                else -> _navigation.value = Navigation.GameByCapacity(typeChampionship)
            }
        }
        val itemSelectBuiltItem = SelectItem(getResources.getString(R.string.by_built), isBlocked(typeChampionship, BY_BUILT), BY_BUILT) {
            when {
                isBlocked(typeChampionship, BY_BUILT) -> _dialog.value = Dialog.DialogLevelLock
                else -> _navigation.value = Navigation.GameByBuilt(typeChampionship)
            }
        }
        listOf.add(itemHeader)
        listOf.add(itemSelectImageItem)
        listOf.add(itemSelectNameItem)
        listOf.add(itemSelectShieldItem)
        listOf.add(itemSelectCapacityItem)
        listOf.add(itemSelectBuiltItem)

        return listOf
    }

    private fun isBlocked(typeChampionship: TypeChampionship, typeGame: TypeGame): Boolean {
        val progressionGame = when(typeChampionship) {
            SPAIN_FIRST_DIVISION -> when(typeGame) {
                BY_IMAGE -> 0
                BY_NAME -> 1
                BY_SHIELD -> 2
                BY_CAPACITY -> 3
                BY_BUILT -> 4
            }
            ENGLAND_FIRST_DIVISION -> when(typeGame) {
                BY_IMAGE -> 5
                BY_NAME -> 6
                BY_SHIELD -> 7
                BY_CAPACITY -> 8
                BY_BUILT -> 9
            }
            ITALY_FIRST_DIVISION -> when(typeGame) {
                BY_IMAGE -> 10
                BY_NAME -> 11
                BY_SHIELD -> 12
                BY_CAPACITY -> 13
                BY_BUILT -> 14
            }
            GERMAIN_FIRST_DIVISION -> when(typeGame) {
                BY_IMAGE -> 15
                BY_NAME -> 16
                BY_SHIELD -> 17
                BY_CAPACITY -> 18
                BY_BUILT -> 19
            }
            FRENCH_FIRST_DIVISION -> when(typeGame) {
                BY_IMAGE -> 20
                BY_NAME -> 21
                BY_SHIELD -> 22
                BY_CAPACITY -> 23
                BY_BUILT -> 24
            }
        }
        return progressionGame > progressUser
    }

    fun goToProfile() {
        _navigation.value = Navigation.Profile
    }

    sealed class Dialog {
        object DialogLevelLock : Dialog()
    }
    sealed class Navigation {
        data class GameByImage(val championship: TypeChampionship) : Navigation()
        data class GameByName(val championship: TypeChampionship) : Navigation()
        data class GameByShield(val championship: TypeChampionship) : Navigation()
        data class GameByCapacity(val championship: TypeChampionship) : Navigation()
        data class GameByBuilt(val championship: TypeChampionship) : Navigation()
        object Profile : Navigation()
    }

    sealed class UiModel {
        data class ContentCareerMode(val items: List<SelectItem>, val mode: ModeGame) : UiModel()
        data class UserData(val user: User) : UiModel()
    }

    companion object {
        private val TAG = SelectViewModel::class.java.simpleName
    }
}