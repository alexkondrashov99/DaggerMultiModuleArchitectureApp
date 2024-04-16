package com.testarchitecture.dynamicfeatureapp.di

import com.testarchitecture.dynamicfeatureapp.dynamicfeature.DynamicFeatureNavigationImpl
import com.testarchitecture.mylibrary.DynamicFeatureNavigation
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun bindDynamicFeatureNavigation(navigationImpl: DynamicFeatureNavigationImpl): DynamicFeatureNavigation
}