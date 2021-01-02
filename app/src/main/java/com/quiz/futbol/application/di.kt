package com.quiz.futbol.application

import android.app.Activity
import android.app.Application
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.quiz.data.datasource.DataBaseSource
import com.quiz.data.datasource.FirestoreDataSource
import com.quiz.data.datasource.SharedPreferencesLocalDataSource
import com.quiz.data.repository.AppsRecommendedRepository
import com.quiz.data.repository.SharedPreferencesRepository
import com.quiz.data.repository.StadiumByIdRepository
import com.quiz.data.repository.UserRepository
import com.quiz.futbol.datasource.DataBaseSourceImpl
import com.quiz.futbol.datasource.FirestoreDataSourceImpl
import com.quiz.futbol.managers.DialogCustomManager
import com.quiz.futbol.managers.SharedPrefsDataSource
import com.quiz.futbol.ui.follows.FollowsFragment
import com.quiz.futbol.ui.follows.FollowsViewModel
import com.quiz.futbol.ui.game.GameFragment
import com.quiz.futbol.ui.game.GameViewModel
import com.quiz.futbol.ui.login.LoginFragment
import com.quiz.futbol.ui.login.LoginViewModel
import com.quiz.futbol.ui.profile.ProfileFragment
import com.quiz.futbol.ui.profile.ProfileViewModel
import com.quiz.futbol.ui.profileEdit.ProfileEditFragment
import com.quiz.futbol.ui.profileEdit.ProfileEditViewModel
import com.quiz.futbol.ui.result.ResultFragment
import com.quiz.futbol.ui.result.ResultViewModel
import com.quiz.futbol.ui.select.SelectFragment
import com.quiz.futbol.ui.select.SelectViewModel
import com.quiz.futbol.utils.GetResources
import com.quiz.usecases.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        koin.loadModules(listOf(
            appModule,
            dataModule,
            scopesModule
        ))
        koin.createRootScope()
    }
}

private val appModule = module {
    factory { Firebase.firestore }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single {GetResources(get())}
    factory { (activity: Activity) -> DialogCustomManager(activity)}
    factory<DataBaseSource> { DataBaseSourceImpl() }
    factory<FirestoreDataSource> { FirestoreDataSourceImpl(get()) }
    factory<SharedPreferencesLocalDataSource> { SharedPrefsDataSource(get()) }
}

val dataModule = module {
    factory { StadiumByIdRepository(get()) }
    factory { AppsRecommendedRepository(get()) }
    factory { SharedPreferencesRepository(get()) }
    factory { UserRepository(get()) }
}

private val scopesModule = module {
    scope(named<LoginFragment>()) {
        viewModel { LoginViewModel(get(), get(), get()) }
        scoped { SetUUID(get()) }
        scoped { GetUUID(get()) }
        scoped { SaveUser(get()) }
    }
    scope(named<SelectFragment>()) {
        viewModel { SelectViewModel(get(), get(), get()) }
        scoped { GetUUID(get()) }
        scoped { GetUser(get()) }
    }
    scope(named<ProfileFragment>()) {
        viewModel { ProfileViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
        scoped { GetUUID(get()) }
        scoped { GetUser(get()) }
        scoped { GetUserLevel(get()) }
        scoped { GetCountFollowing(get()) }
        scoped { GetCountFollowers(get()) }
        scoped { GetUserStageCompleted(get()) }
        scoped { GetGlobalArchievements(get()) }
        scoped { GetPersonalArchievements(get()) }
    }
    scope(named<FollowsFragment>()) {
        viewModel { FollowsViewModel(get(), get(), get(), get(), get(), get()) }
        scoped { GetFollowing(get()) }
        scoped { GetFollowers(get()) }
        scoped { GetUUID(get()) }
        scoped { GetUser(get()) }
        scoped { SetUnfollower(get()) }
        scoped { SetUnfollowing(get()) }
    }
    scope(named<ProfileEditFragment>()) {
        viewModel { ProfileEditViewModel(get(), get(), get()) }
        scoped { GetUUID(get()) }
        scoped { GetUser(get()) }
        scoped { SaveUser(get())}
    }
    scope(named<GameFragment>()) {
        viewModel { GameViewModel(get()) }
        scoped { GetStadiumById(get()) }
    }
    scope(named<ResultFragment>()) {
        viewModel { ResultViewModel(get()) }
        scoped { GetPaymentDone(get()) }
    }
}
