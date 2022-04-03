package com.ozimos.made

import android.app.Application
import com.ozimos.core.di.databaseModule
import com.ozimos.core.di.remoteModule
import com.ozimos.core.di.repositoryModule
import com.ozimos.made.di.useCaseModule
import com.ozimos.made.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    databaseModule,
                    remoteModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}