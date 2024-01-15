package com.testarchitecture.dashboard.di

import android.app.Application
import com.testarchitecture.core.di.CoreComponent
import com.testarchitecture.dashboard.MainActivity
import dagger.BindsInstance
import dagger.Component

@DashboardScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [DashboardModule::class]
)
interface DashboardComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent,
                   appModule: DashboardModule,
                   @BindsInstance application: Application): DashboardComponent
    }

    fun inject(activity: MainActivity)

}