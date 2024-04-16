package com.testarchitecture.feature1.impl

import android.content.Context
import android.content.Intent
import com.testarchitecture.feature1.api.Feature1Navigation
import com.testarchitecture.feature1.presentation.Feature1Activity
import javax.inject.Inject

class Feature1NavigationImpl @Inject constructor(
): Feature1Navigation {
    override fun navigateFeature1(context: Context) {
        context.startActivity(Intent(context, Feature1Activity::class.java))
    }
}