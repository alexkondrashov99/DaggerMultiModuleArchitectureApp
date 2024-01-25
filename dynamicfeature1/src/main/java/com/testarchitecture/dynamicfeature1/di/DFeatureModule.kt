package com.testarchitecture.dynamicfeature1.di

import com.testarchitecture.dynamicfeature1.data.implementation.DFeature1RepositoryImpl
import com.testarchitecture.dynamicfeature1.data.interfaces.DFeature1Repository
import dagger.Module
import dagger.Provides

@Module
class DFeatureModule {

    @Provides
    fun provideDFeature1Repository(): DFeature1Repository = DFeature1RepositoryImpl()
}