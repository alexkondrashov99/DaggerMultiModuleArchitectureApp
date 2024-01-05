package com.testarchitecture.dynamicfeatureapp.dynamicfeature

sealed class DynamicFeatureModule(
    val name: String,
    val entryPoint: String
) {

    object Feature1 : DynamicFeatureModule(
        name = "feature1",
        entryPoint = "com.testarchitecture.dynamicfeature1.Feature1Activity"
    )

    object Feature2 : DynamicFeatureModule(
        name = "feature2",
        entryPoint = "com.testarchitecture.dynamicfeature2.Feature2Activity"
    )
}
