package com.testarchitecture.dynamicfeature1.di

import com.testarchitecture.dynamicfeature1.Feature1Activity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class Feature1ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeFeature1Activity(): Feature1Activity

}