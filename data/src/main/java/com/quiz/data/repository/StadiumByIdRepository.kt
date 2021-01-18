package com.quiz.data.repository

import com.quiz.data.datasource.DataBaseSource
import com.quiz.domain.Stadium

class StadiumByIdRepository(private val dataBaseSource: DataBaseSource) {

    suspend fun getStadiumById(id: Int, championship: String): Stadium = dataBaseSource.getStadiumById(id, championship)

}