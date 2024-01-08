package com.testarchitecture.dynamicfeatureapp.di

import com.testarchitecture.dynamicfeatureapp.DataProviderImpl
import com.testarchitecture.dynamicfeatureapp.SomeDataProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideDataProvider(): SomeDataProvider = DataProviderImpl()
}