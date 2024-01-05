package com.testarchitecture.dynamicfeatureapp.dynamicfeature

sealed class DynamicFeatureModule(
    val name: String,
    val entryPoint: String
) {

    object Feature1 : DynamicFeatureModule(
        name = "feature1",
        entryPoint = "com.testarchitecture.dynamicfeature1.Feature1Activity"
    )
}
