package com.quiz.data.datasource

import com.quiz.domain.App
import com.quiz.domain.Stadium

interface DataBaseSource {
    suspend fun getStadiumById(id: Int, championship: String): Stadium
    suspend fun getAppsRecommended(): MutableList<App>
}