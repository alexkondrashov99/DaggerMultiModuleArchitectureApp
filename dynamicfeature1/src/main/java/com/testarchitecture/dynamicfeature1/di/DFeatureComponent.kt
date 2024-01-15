package com.testarchitecture.dynamicfeature1.di

import android.app.Application
import com.testarchitecture.dynamicfeature1.Feature1Activity
import com.testarchitecture.dynamicfeature1.Feature1AdditionalActivity
import com.testarchitecture.core.di.CoreComponent
import dagger.BindsInstance
import dagger.Component

@DFeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [DFeatureModule::class])
interface DFeatureComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent,
                   dFeatureModule: DFeatureModule,
                   @BindsInstance application: Application): DFeatureComponent
    }

    fun inject(activity: Feature1Activity)

    fun inject(activity: Feature1AdditionalActivity)
}