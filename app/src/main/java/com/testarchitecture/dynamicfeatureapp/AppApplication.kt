package com.testarchitecture.dynamicfeatureapp

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import com.testarchitecture.core.CoreComponentProvider
import com.testarchitecture.core.di.CoreComponent
import com.testarchitecture.core.di.DaggerCoreComponent
import com.testarchitecture.dynamicfeatureapp.di.AppModule
import com.testarchitecture.dynamicfeatureapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class AppApplication : CoreComponentProvider, DaggerApplication()  {

    lateinit var coreComponent: CoreComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .coreComponent(provideCoreComponent())
            .build()
    }

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent.factory().create(this)
        }
        return coreComponent
    }

    companion object {

        private val TAG = AppApplication::class.java.simpleName
    }
}