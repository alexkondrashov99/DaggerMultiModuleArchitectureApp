package com.testarchitecture.dynamicfeatureapp

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import com.testarchitecture.dynamicfeatureapp.di.AppComponent
import com.testarchitecture.dynamicfeatureapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class AppApplication : Application(), AppComponentProvider {

    lateinit var appComponent: AppComponent

    override fun provideAppComponent() = appComponent

    override fun onCreate() {

        super.onCreate()
        Log.d(TAG, "onCreate() called")


        DaggerAppComponent.factory().create(this).also {
            appComponent = it
        }.inject(this)

        instance = this
    }


    companion object {

        private val TAG = AppApplication::class.java.simpleName

        lateinit var instance: AppApplication

        private fun getAppApplication(context: Context): AppApplication? {
            return if (context.applicationContext is AppApplication) {
                context.applicationContext as AppApplication
            } else {
                when (context) {
                    is Activity -> context.application as? AppApplication
                    is Service -> context.application as? AppApplication
                    is ContextWrapper -> (context.baseContext as? Activity)?.application as? AppApplication
                    else -> null
                }
            }
        }
    }
}


interface AppComponentProvider {
    fun provideAppComponent(): AppComponent
}

fun Activity.appComponent() = (applicationContext as? AppComponentProvider)?.provideAppComponent()
    ?: throw IllegalStateException("CoreComponentProvider not implemented: $applicationContext")