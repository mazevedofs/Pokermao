package com.marina.pokermao

import android.app.Application
import com.facebook.stetho.Stetho
import com.marina.pokermao.di.networkModule
import com.marina.pokermao.di.repositoryModule
import com.marina.pokermao.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )

        }
    }

}
