package com.testarchitecture.feature2.impl

import android.content.Context
import android.content.Intent
import android.util.Log
import com.testarchitecture.feature2.presentation.Feature2Activity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class Feature2NavigationImpl @AssistedInject constructor(
    @Assisted("context") private val context: Context
): com.testarchitecture.feature2.api.Feature2Navigation {
    override fun navigateFeature2() {
        context.startActivity(Intent(context, Feature2Activity::class.java))
    }
}