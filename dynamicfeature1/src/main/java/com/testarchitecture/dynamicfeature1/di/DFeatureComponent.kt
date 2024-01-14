package com.testarchitecture.dynamicfeature1.di

import android.app.Application
import com.testarchitecture.dynamicfeature1.Feature1Activity
import com.testarchitecture.dynamicfeature1.Feature1AdditionalActivity
import com.testarchitecture.dynamicfeature1.data.interfaces.DFeature1Singletone
import com.testarchitecture.dynamicfeatureapp.SomeDataProvider
import com.testarchitecture.dynamicfeatureapp.di.ActivityScope
import com.testarchitecture.dynamicfeatureapp.di.AppComponent
import com.testarchitecture.dynamicfeatureapp.di.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
@DFeatureScope
@Component(
    dependencies = [AppComponent::class],
    modules = [AndroidSupportInjectionModule::class, DFeatureModule::class])
interface DFeatureComponent {

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent,
                   dFeatureModule: DFeatureModule,
                   @BindsInstance application: Application): DFeatureComponent
    }

    fun inject(activity: Feature1Activity)

    fun inject(activity: Feature1AdditionalActivity)
}