package com.quiz.futbol.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quiz.domain.User
import com.quiz.futbol.common.ScopedViewModel


class LoginViewModel : ScopedViewModel() {

    private val _loginWithGoogle = MutableLiveData<UiModel>()
    val loginWithGoogle: LiveData<UiModel> = _loginWithGoogle

    fun saveUser(user: User) {
        _loginWithGoogle.value = UiModel.LoginSuccess(user)
    }

    sealed class UiModel {
        data class LoginSuccess(val user: User) : UiModel()
    }

    companion object {
        private val TAG = LoginViewModel::class.java.simpleName
    }
}