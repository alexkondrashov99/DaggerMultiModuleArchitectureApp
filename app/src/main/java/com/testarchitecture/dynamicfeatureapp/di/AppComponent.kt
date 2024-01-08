package com.testarchitecture.dynamicfeatureapp.di

import android.app.Application
import com.testarchitecture.dynamicfeatureapp.AppApplication
import com.testarchitecture.dynamicfeatureapp.SomeDataProvider
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class
])
interface AppComponent : AndroidInjector<AppApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    //fun someDataProvider(): SomeDataProvider

}