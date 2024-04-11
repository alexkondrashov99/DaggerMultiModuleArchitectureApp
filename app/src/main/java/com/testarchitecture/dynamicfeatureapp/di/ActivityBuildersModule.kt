package com.testarchitecture.dynamicfeatureapp.di

import com.testarchitecture.dashboard.DashboardActivity
import com.testarchitecture.dashboard.di.DashboardModule
import com.testarchitecture.dynamicfeatureapp.LegacyDashboardActivity
import com.testarchitecture.feature1.impl.Feature1DaggerModule
import com.testarchitecture.feature1.presentation.Feature1Activity
import com.testarchitecture.feature1.impl.Feature1Module
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [])
    abstract fun legacyDashboardActivity(): LegacyDashboardActivity

    @ContributesAndroidInjector(modules = [DashboardModule::class, Feature1DaggerModule::class])
    abstract fun dashboardActivity(): DashboardActivity

    @ContributesAndroidInjector(modules = [Feature1Module::class, AppModule::class])
    abstract fun feature1Activity(): Feature1Activity
}