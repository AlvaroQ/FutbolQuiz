package com.quiz.data.datasource

import com.quiz.domain.User

interface SharedPreferencesLocalDataSource  {
    var paymentDone: Boolean
    var currentUser: User
}