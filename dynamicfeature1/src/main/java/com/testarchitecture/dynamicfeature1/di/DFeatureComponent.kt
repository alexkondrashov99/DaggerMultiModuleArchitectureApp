package com.testarchitecture.dynamicfeature1.di

import com.testarchitecture.dynamicfeature1.Feature1Activity
import com.testarchitecture.dynamicfeatureapp.di.ActivityScope
import com.testarchitecture.dynamicfeatureapp.di.AppComponent
import com.testarchitecture.dynamicfeatureapp.di.AppModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    //modules = [DFeatureModule::class, Feature1ActivityBuildersModule::class])
    modules = [AndroidSupportInjectionModule::class, DFeatureModule::class, Feature1ActivityBuildersModule::class, AppModule::class])
interface DFeatureComponent {

    @Component.Factory
    interface Factory {
        // Takes an instance of AppComponent when creating
        // an instance of LoginComponent
        fun create(appComponent: AppComponent): DFeatureComponent
    }

    fun inject(activity: Feature1Activity)
}