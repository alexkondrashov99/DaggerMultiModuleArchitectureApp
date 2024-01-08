package com.testarchitecture.dynamicfeatureapp

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import com.testarchitecture.dynamicfeatureapp.di.AppComponent
import com.testarchitecture.dynamicfeatureapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class AppApplication : DaggerApplication(), AppComponentProvider {

    lateinit var appComponent: AppComponent

    override fun provideAppComponent() = appComponent

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate() called")

        this.applicationInjector()
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val build = DaggerAppComponent.builder().application(this).build()
        appComponent = build
        build.inject(this)
        return build
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