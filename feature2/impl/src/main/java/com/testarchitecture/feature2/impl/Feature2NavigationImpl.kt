package com.testarchitecture.feature2.impl

import android.content.Context
import android.content.Intent
import com.testarchitecture.feature2.api.Feature2Navigation
import com.testarchitecture.feature2.presentation.Feature2Activity
import javax.inject.Inject

class Feature2NavigationImpl @Inject constructor(
): Feature2Navigation {
    override fun navigateFeature2(context: Context) {
        context.startActivity(Intent(context, Feature2Activity::class.java))
    }
}