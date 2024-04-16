package com.testarchitecture.dynamicfeature1.di

import android.app.Application
import com.testarchitecture.dynamicfeature1.DynamicFeature1Activity
import com.testarchitecture.core.di.CoreComponent
import com.testarchitecture.feature1.impl.Feature1ApiModule

import dagger.BindsInstance
import dagger.Component

@DFeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [DFeatureModule::class, Feature1ApiModule::class])
interface DFeatureComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent,
                   dFeatureModule: DFeatureModule,
                   @BindsInstance application: Application): DFeatureComponent
    }

    fun inject(activity: DynamicFeature1Activity)
}