package com.testarchitecture.core.di

import com.testarchitecture.core.DataProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {
    @Singleton
    @Provides
    fun provideDataProvider(): com.testarchitecture.core.SomeDataProvider = DataProviderImpl()
}