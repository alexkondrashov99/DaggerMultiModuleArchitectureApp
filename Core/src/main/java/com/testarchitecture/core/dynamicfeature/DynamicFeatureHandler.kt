package com.testarchitecture.core.dynamicfeature

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest

interface DynamicFeatureHandler {
    fun showError(error: DynamicFeatureInstallError)
    fun getContext() : Context
    fun showProgress(shouldShowProgress: Boolean)
    fun getDynamicFeatureResultLauncher(): ActivityResultLauncher<IntentSenderRequest>
}