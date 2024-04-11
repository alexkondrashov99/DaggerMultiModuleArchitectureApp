package com.testarchitecture.feature1.impl

import android.content.Context
import android.content.Intent
import android.util.Log
import com.testarchitecture.feature1.presentation.Feature1Activity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class Feature1NavigationImpl @AssistedInject constructor(
    @Assisted("context") private val context: Context
): com.testarchitecture.feature1.api.Feature1Navigation {
    override fun navigateFeature1() {
        context.startActivity(Intent(context, Feature1Activity::class.java))
    }
}