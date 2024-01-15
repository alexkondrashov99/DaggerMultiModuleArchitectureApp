package com.testarchitecture.dashboard.dynamicfeature

import android.content.Context

interface DynamicFeatureHandler {
    fun showError(error: Exception)
    fun getContext() : Context
    fun showProgress(shouldShowProgress: Boolean)
    fun navigateFeature(path: String)
}