package com.quiz.futbol.ui.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quiz.futbol.R
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.futbol.utils.Constants.ModeGame
import com.quiz.futbol.utils.Constants.ModeGame.*
import com.quiz.futbol.utils.Constants.TypeChampionship
import com.quiz.futbol.utils.Constants.TypeChampionship.*
import com.quiz.futbol.utils.Constants.TypeGame.*
import com.quiz.futbol.utils.GetResources
import com.quiz.futbol.utils.log

class SelectViewModel(private val getResources: GetResources) : ScopedViewModel() {

    private val _loadBottomSheetData = MutableLiveData<UiModel>()
    val loadBottomSheetData: LiveData<UiModel> = _loadBottomSheetData

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private val _dialog = MutableLiveData<Dialog>()
    val dialog: LiveData<Dialog> = _dialog

    init {
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_SELECT_GAME)
    }

    fun loadTrainingMode() {
        _loadBottomSheetData.value = UiModel.ContentCareerMode(loadSelectAllItems(), TRAINIG)
    }

    fun loadCareerMode() {
        _loadBottomSheetData.value = UiModel.ContentCareerMode(loadSelectAllItems(), CARRER)
    }

    private fun loadSelectAllItems(): MutableList<SelectItem> {
        val listOf = mutableListOf<SelectItem>()
        listOf.addAll(loadSelectItems(SPAIN_FIRST_DIVISION))
        listOf.addAll(loadSelectItems(ENGLAND_FIRST_DIVISION))
        listOf.addAll(loadSelectItems(ITALY_FIRST_DIVISION))
        listOf.addAll(loadSelectItems(GERMAIN_FIRST_DIVISION))
        listOf.addAll(loadSelectItems(FRENCH_FIRST_DIVISION))
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

        val itemSelectImageItem = SelectItem(getResources.getString(R.string.by_image), false, BY_IMAGE) {
            when {
                //isBlocked -> _dialog.value = Dialog.DialogLevelLock
                //!isLogged -> _dialog.value = Dialog.DialogSignInWithGoogle
                else -> _navigation.value = Navigation.GameByImage(typeChampionship)
            }
        }
        val itemSelectNameItem = SelectItem(getResources.getString(R.string.by_name), true, BY_NAME) {
            _navigation.value = Navigation.GameByName(typeChampionship)
        }
        val itemSelectCapacityItem = SelectItem(getResources.getString(R.string.by_capacity), true, BY_CAPACITY) {
            _navigation.value = Navigation.GameByCapacity(typeChampionship)
        }
        val itemSelectBuiltItem = SelectItem(getResources.getString(R.string.by_built), true, BY_BUILT) {
            _navigation.value = Navigation.GameByBuilt(typeChampionship)
        }
        listOf.add(itemHeader)
        listOf.add(itemSelectImageItem)
        listOf.add(itemSelectNameItem)
        listOf.add(itemSelectCapacityItem)
        listOf.add(itemSelectBuiltItem)

        return listOf
    }

    sealed class Dialog {
        object DialogLevelLock : Dialog()
        object DialogSignInWithGoogle : Dialog()
    }
    sealed class Navigation {
        data class GameByImage(val championship: TypeChampionship) : Navigation()
        data class GameByName(val championship: TypeChampionship) : Navigation()
        data class GameByCapacity(val championship: TypeChampionship) : Navigation()
        data class GameByBuilt(val championship: TypeChampionship) : Navigation()
    }

    sealed class UiModel {
        data class ContentCareerMode(val items: List<SelectItem>, val mode: ModeGame) : UiModel()
    }

    companion object {
        private val TAG = SelectViewModel::class.java.simpleName
    }
}