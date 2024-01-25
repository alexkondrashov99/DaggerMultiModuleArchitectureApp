package com.testarchitecture.dashboard.di

import dagger.Module
import dagger.Provides

@Module
class DashboardModule {

    @Provides
    fun provideString() = "DashboardModuleStringGaGa"
}