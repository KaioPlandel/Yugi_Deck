package com.plandel.compose002

import android.app.Application
import com.plandel.compose002.BuildConfig.DEBUG
import com.plandel.compose002.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class YugiDeckApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        startKoin {
            androidLogger(org.koin.core.logger.Level.DEBUG)
            androidContext(this@YugiDeckApplication)
            modules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule,
                databaseModule))
        }
    }
}
