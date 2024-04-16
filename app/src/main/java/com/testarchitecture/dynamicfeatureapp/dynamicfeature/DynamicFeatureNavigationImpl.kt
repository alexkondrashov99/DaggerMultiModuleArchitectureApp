package com.testarchitecture.dynamicfeatureapp.dynamicfeature

import android.content.Context
import android.content.Intent
import com.testarchitecture.mylibrary.DynamicFeatureNavigation
import javax.inject.Inject

class DynamicFeatureNavigationImpl @Inject constructor(): DynamicFeatureNavigation {
    override fun navigateDynamicFeature1(context: Context) {
        val intent = Intent().apply {
            setClassName(context, DynamicFeatureModule.Feature1.entryPoint)
        }
        context.startActivity(intent)
    }

}