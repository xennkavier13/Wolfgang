package com.csit284.wolfgang.app

import android.app.Application

class DataManagement : Application() {
    var username : String = ""
    var password : String = ""
    var email : String = ""
    override fun onCreate() {
        super.onCreate()
    }

}