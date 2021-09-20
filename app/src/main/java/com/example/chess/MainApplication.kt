package com.example.chess

import android.app.Application
import android.content.Context
import com.example.chess.di.AppComponent
import com.example.chess.di.DaggerAppComponent

class MainApplication: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is MainApplication -> appComponent
        else -> this.applicationContext.appComponent
    }