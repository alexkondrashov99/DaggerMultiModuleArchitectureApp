package com.testarchitecture.dynamicfeatureapp.di

import com.testarchitecture.dynamicfeatureapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeMainActivity(): MainActivity

}