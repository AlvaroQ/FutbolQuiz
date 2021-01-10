package com.quiz.data.datasource


interface SharedPreferencesLocalDataSource  {
    var paymentDone: Boolean
    var uuid: String
    var timestampGame: Long
    var levelLocal: Int
}