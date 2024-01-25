package com.testarchitecture.dynamicfeatureapp.di

import android.app.Application
import com.testarchitecture.core.di.CoreComponent
import com.testarchitecture.dynamicfeatureapp.AppApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ActivityBindingModule::class, AndroidInjectionModule::class, AppModule::class]
)
interface AppComponent: AndroidInjector<AppApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application):
                AppComponent.Builder
        fun coreComponent(coreComponent: CoreComponent):
                AppComponent.Builder
        fun build(): AppComponent
    }
}