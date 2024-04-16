package com.testarchitecture.dynamicfeatureapp.di

import android.app.Application
import com.testarchitecture.core.di.CoreComponent
import com.testarchitecture.dynamicfeatureapp.AppApplication
import com.testarchitecture.feature1.impl.Feature1ApiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ActivityBuildersModule::class, AndroidInjectionModule::class, AppModule::class, Feature1ApiModule::class]
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