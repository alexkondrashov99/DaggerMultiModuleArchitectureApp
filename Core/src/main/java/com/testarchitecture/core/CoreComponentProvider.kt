package com.testarchitecture.core

import android.app.Activity
import com.testarchitecture.core.di.CoreComponent


interface CoreComponentProvider {
    fun provideCoreComponent(): CoreComponent
}

fun Activity.coreComponent() = (applicationContext as? CoreComponentProvider)?.provideCoreComponent()
    ?: throw IllegalStateException("CoreComponentProvider not implemented: $applicationContext")