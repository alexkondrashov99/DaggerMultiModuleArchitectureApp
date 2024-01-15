package com.testarchitecture.dynamicfeatureapp.di

import android.app.Application
import com.testarchitecture.dynamicfeatureapp.AppApplication
import com.testarchitecture.dynamicfeatureapp.MainActivity
import com.testarchitecture.dynamicfeatureapp.SomeDataProvider
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(application: Application)

    fun inject(activity: MainActivity)

    fun someDataProvider(): SomeDataProvider

}