package com.anupkunwar.composeplayground

import android.app.Application
import com.anupkunwar.composeplayground.data.AppComponent

class MyApp : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent()
    }
}