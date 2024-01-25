package com.testarchitecture.feature1.di

import com.testarchitecture.feature1.data.implementation.Feature1RepositoryImpl
import com.testarchitecture.feature1.data.interfaces.Feature1Repository
import dagger.Module
import dagger.Provides

@Module
class Feature1Module {

    @Provides
    fun provideDFeature1Repository(): Feature1Repository = Feature1RepositoryImpl()
}