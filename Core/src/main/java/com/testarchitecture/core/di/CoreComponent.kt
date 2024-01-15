package com.testarchitecture.core.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    CoreModule::class
])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): CoreComponent
    }

    fun inject(application: Application)

    fun someDataProvider(): com.testarchitecture.core.SomeDataProvider

}