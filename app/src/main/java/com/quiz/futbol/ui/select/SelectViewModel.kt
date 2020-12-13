package com.quiz.futbol.ui.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quiz.futbol.R
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.futbol.managers.AnalyticsManager
import com.quiz.futbol.utils.Constants.TypeChampionship
import com.quiz.futbol.utils.Constants.TypeChampionship.*
import com.quiz.futbol.utils.Constants.TypeGame.*
import com.quiz.futbol.utils.GetResources

class SelectViewModel(private val getResources: GetResources) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    init {
        AnalyticsManager.analyticsScreenViewed(AnalyticsManager.SCREEN_SELECT_GAME)
        refresh()
    }

    private fun refresh() {

        val listOf = mutableListOf<SelectItem>()

        // España - Primera
        val spainFirstHeader = SelectItem(getResources.getString(R.string.first_division), false, null, SPAIN_FIRST_DIVISION) {
            _navigation.value = Navigation.GameByImage(SPAIN_FIRST_DIVISION)
        }

        val spainFirstSelectImageItem = SelectItem(getResources.getString(R.string.by_image), false, BY_IMAGE) {
            _navigation.value = Navigation.GameByImage(SPAIN_FIRST_DIVISION)
        }
        val spainFirstSelectNameItem = SelectItem(getResources.getString(R.string.by_name), true, BY_NAME) {
            _navigation.value = Navigation.GameByName(SPAIN_FIRST_DIVISION)
        }
        val spainFirstSelectCapacityItem = SelectItem(getResources.getString(R.string.by_capacity), true, BY_CAPACITY) {
            _navigation.value = Navigation.GameByCapacity(SPAIN_FIRST_DIVISION)
        }
        val spainFirstSelectBuiltItem = SelectItem(getResources.getString(R.string.by_built), true, BY_BUILT) {
            _navigation.value = Navigation.GameByBuilt(SPAIN_FIRST_DIVISION)
        }
        listOf.add(spainFirstHeader)
        listOf.add(spainFirstSelectImageItem)
        listOf.add(spainFirstSelectNameItem)
        listOf.add(spainFirstSelectCapacityItem)
        listOf.add(spainFirstSelectBuiltItem)

        // España - Segunda
        val spainSecondHeader = SelectItem(getResources.getString(R.string.second_division), false, null, SPAIN_FIRST_DIVISION) {
            _navigation.value = Navigation.GameByImage(SPAIN_FIRST_DIVISION)
        }
        val spainSecondSelectImageItem = SelectItem(getResources.getString(R.string.by_image), true, BY_IMAGE) {
            _navigation.value = Navigation.GameByImage(SPAIN_FIRST_DIVISION)
        }
        val spainSecondSelectNameItem = SelectItem(getResources.getString(R.string.by_name), true, BY_NAME) {
            _navigation.value = Navigation.GameByName(SPAIN_FIRST_DIVISION)
        }
        val spainSecondSelectCapacityItem = SelectItem(getResources.getString(R.string.by_capacity), true, BY_CAPACITY) {
            _navigation.value = Navigation.GameByCapacity(SPAIN_FIRST_DIVISION)
        }
        val spainSecondSelectBuiltItem = SelectItem(getResources.getString(R.string.by_built), true, BY_BUILT) {
            _navigation.value = Navigation.GameByBuilt(SPAIN_FIRST_DIVISION)
        }
        listOf.add(spainSecondHeader)
        listOf.add(spainSecondSelectImageItem)
        listOf.add(spainSecondSelectNameItem)
        listOf.add(spainSecondSelectCapacityItem)
        listOf.add(spainSecondSelectBuiltItem)


        // Inglaterra - Primera
        val englandFirstHeader = SelectItem(getResources.getString(R.string.first_division), false, null, ENGLAND_FIRST_DIVISION) {
            _navigation.value = Navigation.GameByImage(ENGLAND_FIRST_DIVISION)
        }
        val englandFirstSelectImageItem = SelectItem(getResources.getString(R.string.by_image), true, BY_IMAGE) {
            _navigation.value = Navigation.GameByImage(ENGLAND_FIRST_DIVISION)
        }
        val englandFirstSelectNameItem = SelectItem(getResources.getString(R.string.by_name), true, BY_NAME) {
            _navigation.value = Navigation.GameByName(ENGLAND_FIRST_DIVISION)
        }
        val englandFirstSelectCapacityItem = SelectItem(getResources.getString(R.string.by_capacity), true, BY_CAPACITY) {
            _navigation.value = Navigation.GameByCapacity(ENGLAND_FIRST_DIVISION)
        }
        val englandFirstSelectBuiltItem = SelectItem(getResources.getString(R.string.by_built), true, BY_BUILT) {
            _navigation.value = Navigation.GameByBuilt(ENGLAND_FIRST_DIVISION)
        }
        listOf.add(englandFirstHeader)
        listOf.add(englandFirstSelectImageItem)
        listOf.add(englandFirstSelectNameItem)
        listOf.add(englandFirstSelectCapacityItem)
        listOf.add(englandFirstSelectBuiltItem)

        // Inglaterra - Segunda
        val englandSecondHeader = SelectItem(getResources.getString(R.string.second_division), false, null, ENGLAND_SECOND_DIVISION) {
            _navigation.value = Navigation.GameByImage(ENGLAND_SECOND_DIVISION)
        }
        val englandSecondSelectImageItem = SelectItem(getResources.getString(R.string.by_image), true, BY_IMAGE) {
            _navigation.value = Navigation.GameByImage(ENGLAND_SECOND_DIVISION)
        }
        val englandSecondSelectNameItem = SelectItem(getResources.getString(R.string.by_name), true, BY_NAME) {
            _navigation.value = Navigation.GameByName(ENGLAND_SECOND_DIVISION)
        }
        val englandSecondSelectCapacityItem = SelectItem(getResources.getString(R.string.by_capacity), true, BY_CAPACITY) {
            _navigation.value = Navigation.GameByCapacity(ENGLAND_SECOND_DIVISION)
        }
        val englandSecondSelectBuiltItem = SelectItem(getResources.getString(R.string.by_built), true, BY_BUILT) {
            _navigation.value = Navigation.GameByBuilt(ENGLAND_SECOND_DIVISION)
        }
        listOf.add(englandSecondHeader)
        listOf.add(englandSecondSelectImageItem)
        listOf.add(englandSecondSelectNameItem)
        listOf.add(englandSecondSelectCapacityItem)
        listOf.add(englandSecondSelectBuiltItem)

        _model.value = UiModel.Content(listOf)
    }

    sealed class Navigation {
        data class GameByImage(val championship: TypeChampionship) : Navigation()
        data class GameByName(val championship: TypeChampionship) : Navigation()
        data class GameByCapacity(val championship: TypeChampionship) : Navigation()
        data class GameByBuilt(val championship: TypeChampionship) : Navigation()
    }

    sealed class UiModel {
        data class Content(val items: List<SelectItem>) : UiModel()
    }
}