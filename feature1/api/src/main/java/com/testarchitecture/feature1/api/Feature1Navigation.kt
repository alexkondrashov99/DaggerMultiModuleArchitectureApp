package com.testarchitecture.feature1.api

import android.content.Context

interface Feature1Navigation {
    fun navigateFeature1()
}

interface Feature1NavigationFactory {
    fun create(context: Context): Feature1Navigation
}