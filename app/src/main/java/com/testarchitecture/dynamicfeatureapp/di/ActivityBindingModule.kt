package com.testarchitecture.dynamicfeatureapp.di

import com.testarchitecture.dashboard.MainActivity
import com.testarchitecture.dashboard.di.DashboardModule
import com.testarchitecture.feature1.Feature1Activity
import com.testarchitecture.feature1.di.Feature1Module
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [Feature1Module::class])
    abstract fun feature1Activity(): Feature1Activity
}