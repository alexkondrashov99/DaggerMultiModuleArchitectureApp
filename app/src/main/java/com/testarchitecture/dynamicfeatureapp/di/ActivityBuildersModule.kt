package com.testarchitecture.dynamicfeatureapp.di

import com.testarchitecture.dashboard.DashboardActivity
import com.testarchitecture.dashboard.di.DashboardModule
import com.testarchitecture.dynamicfeatureapp.LegacyDashboardActivity
import com.testarchitecture.feature1.impl.Feature1ApiModule
import com.testarchitecture.feature1.presentation.Feature1Activity
import com.testarchitecture.feature1.impl.Feature1Module
import com.testarchitecture.feature2.impl.Feature2ApiModule
import com.testarchitecture.feature2.impl.Feature2Module
import com.testarchitecture.feature2.presentation.Feature2Activity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [])
    abstract fun legacyDashboardActivity(): LegacyDashboardActivity

    @ContributesAndroidInjector(modules = [DashboardModule::class, Feature1ApiModule::class, Feature2ApiModule::class, AppModule::class])
    abstract fun dashboardActivity(): DashboardActivity

    @ContributesAndroidInjector(modules = [Feature1Module::class, Feature2ApiModule::class, AppModule::class])
    abstract fun feature1Activity(): Feature1Activity

    @ContributesAndroidInjector(modules = [Feature2Module::class, Feature1ApiModule::class, AppModule::class])
    abstract fun feature2Activity(): Feature2Activity
}