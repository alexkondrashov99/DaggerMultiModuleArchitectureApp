package com.testarchitecture.core.dynamicfeature

sealed class DynamicFeatureModule(
    val name: String,
    val entryPoint: String
) {

    object Feature1 : DynamicFeatureModule(
        name = "dynamicfeature1",
        entryPoint = "com.testarchitecture.dynamicfeature1.DynamicFeature1Activity"
    )
}
