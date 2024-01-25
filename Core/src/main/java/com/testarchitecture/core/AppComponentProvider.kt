package com.testarchitecture.core

import android.app.Activity
import com.testarchitecture.core.di.CoreComponent


interface AppComponentProvider {
    fun provideCoreComponent(): CoreComponent
}

fun Activity.coreComponent() = (applicationContext as? AppComponentProvider)?.provideCoreComponent()
    ?: throw IllegalStateException("CoreComponentProvider not implemented: $applicationContext")