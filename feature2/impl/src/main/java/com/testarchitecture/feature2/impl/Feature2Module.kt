package com.testarchitecture.feature2.impl


import com.testarchitecture.feature2.data.Feature2RepositoryImpl
import com.testarchitecture.feature2.domain.data.Feature2Repository
import dagger.Module
import dagger.Provides

@Module
class Feature2Module {

    @Provides
    fun provideDFeature2Repository(): Feature2Repository = Feature2RepositoryImpl()
}