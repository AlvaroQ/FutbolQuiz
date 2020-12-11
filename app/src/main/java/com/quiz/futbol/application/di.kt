package com.quiz.futbol.application

import com.quiz.futbol.datasource.FirestoreDataSourceImpl
import com.quiz.data.datasource.FirestoreDataSource
import android.app.Application
import com.quiz.futbol.ui.game.GameFragment
import com.quiz.futbol.ui.game.GameViewModel
import com.quiz.futbol.ui.result.ResultFragment
import com.quiz.futbol.ui.result.ResultViewModel
import com.quiz.futbol.ui.select.SelectFragment
import com.quiz.futbol.ui.select.SelectViewModel
import com.quiz.data.datasource.DataBaseSource
import com.quiz.futbol.datasource.DataBaseSourceImpl
import com.quiz.futbol.ui.ranking.RankingFragment
import com.quiz.futbol.ui.ranking.RankingViewModel
import com.quiz.data.repository.AppsRecommendedRepository
import com.quiz.data.repository.StadiumByIdRepository
import com.quiz.data.repository.RankingRepository
import com.quiz.usecases.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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
    factory<DataBaseSource> { DataBaseSourceImpl() }
    factory<FirestoreDataSource> { FirestoreDataSourceImpl(get()) }
}

val dataModule = module {
    factory { StadiumByIdRepository(get()) }
    factory { AppsRecommendedRepository(get()) }
    factory { RankingRepository(get()) }
}

private val scopesModule = module {
    scope(named<SelectFragment>()) {
        viewModel { SelectViewModel() }
    }
    scope(named<GameFragment>()) {
        viewModel { GameViewModel(get()) }
        scoped { GetStadiumById(get()) }
    }
    scope(named<ResultFragment>()) {
        viewModel { ResultViewModel(get(), get(), get()) }
        scoped { GetRecordScore(get()) }
        scoped { GetAppsRecommended(get()) }
        scoped { SaveTopScore(get()) }
    }
    scope(named<RankingFragment>()) {
        viewModel { RankingViewModel(get()) }
        scoped { GetRankingScore(get()) }
    }
}
