package com.testarchitecture.feature1.impl


import com.testarchitecture.feature1.data.Feature1RepositoryImpl
import com.testarchitecture.feature1.domain.data.Feature1Repository
import dagger.Module
import dagger.Provides

@Module
class Feature1Module {

    @Provides
    fun provideDFeature1Repository(): Feature1Repository = Feature1RepositoryImpl()
}