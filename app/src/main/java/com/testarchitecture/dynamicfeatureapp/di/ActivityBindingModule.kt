package com.testarchitecture.dynamicfeatureapp.di

import com.testarchitecture.dashboard.MainActivity
import com.testarchitecture.dashboard.di.DashboardModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun mainActivity(): MainActivity
}