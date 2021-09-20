package com.example.chess.di

import android.content.Context
import com.example.chess.ui.activity.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    // activity
    fun inject(activity: MainActivity)

    // fragments
}