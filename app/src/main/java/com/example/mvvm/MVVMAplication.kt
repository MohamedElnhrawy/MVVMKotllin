package com.example.mvvm

import android.app.Application
import com.example.mvvm.data.db.AppDatatBase
import com.example.mvvm.data.network.MyApi
import com.example.mvvm.data.network.NetworkConnectionInterceptor
import com.example.mvvm.data.preferences.PreferencesProvider
import com.example.mvvm.data.repositories.QuotesRepository
import com.example.mvvm.data.repositories.userRepository
import com.example.mvvm.ui.auth.AuthViewModelFactory
import com.example.mvvm.ui.profile.ProfileViewModelFactory
import com.example.mvvm.ui.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMAplication : Application() ,KodeinAware{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMAplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatatBase(instance()) }
        bind() from  singleton { PreferencesProvider(instance()) }
        bind() from singleton { userRepository(instance(),instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from singleton { QuotesRepository(instance(),instance(),instance()) }
    }
}