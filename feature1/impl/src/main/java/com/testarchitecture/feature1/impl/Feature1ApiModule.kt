package com.testarchitecture.feature1.impl

import dagger.Binds
import dagger.Module

@Module
abstract class Feature1ApiModule {
    @Binds
    abstract fun bindFeature1Navigation(navigationImpl: Feature1NavigationImpl): com.testarchitecture.feature1.api.Feature1Navigation
}