package com.testarchitecture.feature2.impl

import dagger.Binds
import dagger.Module

@Module
abstract class Feature2ApiModule {
    @Binds
    abstract fun bindFeature2Navigation(navigationImpl: Feature2NavigationImpl): com.testarchitecture.feature2.api.Feature2Navigation
}