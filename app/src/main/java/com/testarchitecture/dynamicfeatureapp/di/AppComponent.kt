package com.testarchitecture.dynamicfeatureapp.di

import android.app.Application
import com.testarchitecture.core.di.CoreComponent
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent,
                   appModule: AppModule,
                   @BindsInstance application: Application): AppComponent
    }
}