package com.testarchitecture.dynamicfeatureapp

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import com.testarchitecture.core.AppComponentProvider
import com.testarchitecture.core.di.CoreComponent
import com.testarchitecture.core.di.DaggerCoreComponent

class AppApplication : Application(), AppComponentProvider {

    lateinit var coreComponent: CoreComponent

    override fun provideCoreComponent() = coreComponent

    override fun onCreate() {

        super.onCreate()
        Log.d(TAG, "onCreate() called")


        DaggerCoreComponent.factory().create(this).also {
            coreComponent = it
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