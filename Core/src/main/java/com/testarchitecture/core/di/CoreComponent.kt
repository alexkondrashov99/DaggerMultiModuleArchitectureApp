package com.testarchitecture.core.di

import android.app.Application
import com.testarchitecture.core.SomeDataProvider
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


    /**
     * Below we have to declare all instances we need to share between modules
     */
    fun someDataProvider(): SomeDataProvider
}