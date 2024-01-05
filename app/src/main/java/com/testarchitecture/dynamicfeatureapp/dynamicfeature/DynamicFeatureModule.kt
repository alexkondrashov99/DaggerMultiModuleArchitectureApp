package com.testarchitecture.dynamicfeatureapp.dynamicfeature

sealed class DynamicFeatureModule(
    val name: String,
    val entryPoint: String
) {

//    object Overdraft : DynamicFeatureModule(
//        name = "overdraft",
//        entryPoint = "com.oxygenbank.overdraft.OverdraftActivity"
//    )
}
