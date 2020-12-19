package com.quiz.futbol.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.quiz.domain.User
import com.quiz.futbol.common.ScopedViewModel
import com.quiz.usecases.SaveUser
import com.quiz.usecases.SetUUID
import com.quiz.usecases.GetUUID
import kotlinx.coroutines.launch


class LoginViewModel(val setUUID: SetUUID,
                     private val getUuid: GetUUID,
                     private val saveUser: SaveUser) : ScopedViewModel() {

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private val _signedInAccount = MutableLiveData<UiModel>()
    val signedInAccount: LiveData<UiModel> = _signedInAccount

    fun loginSucess(uuid: String){
        setUUID(uuid)
        _navigation.value = Navigation.Select
    }

    fun getUid() : String {
        return getUuid.invoke()
    }

    fun saveUserSignIn(user: User) {
        launch {
            setUUID(user.uuid!!)
            saveUser.invoke(user)
        }
    }

    fun googleFirebaseAuth(account: GoogleSignInAccount) {
        _signedInAccount.value = UiModel.GoogleFirebaseAuth(account)
    }

    fun navigationToSelectScreen() {
        _navigation.value = Navigation.Select
    }

    sealed class UiModel {
        data class GoogleFirebaseAuth(val account: GoogleSignInAccount) : UiModel()
    }

    sealed class Navigation {
        object Select : Navigation()
    }

    companion object {
        private val TAG = LoginViewModel::class.java.simpleName
    }
}