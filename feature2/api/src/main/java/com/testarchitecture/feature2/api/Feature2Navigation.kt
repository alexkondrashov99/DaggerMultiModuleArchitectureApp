package com.testarchitecture.feature2.api

import android.content.Context

interface Feature2Navigation {
    fun navigateFeature2()
}

interface Feature2NavigationFactory {
    fun create(context: Context): Feature2Navigation
}