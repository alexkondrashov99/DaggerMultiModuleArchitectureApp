package com.testarchitecture.feature1.impl

import android.content.Context
import com.testarchitecture.feature1.api.Feature1NavigationFactory
import dagger.Binds
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@Module
abstract class Feature1DaggerModule {
//    @Provides
//    fun provideFeature1Navigation(): Feature1Navigation = Feature1NavigationImpl()
//

    abstract fun bindFeature1Navigation(navigationImpl: Feature1NavigationImpl): com.testarchitecture.feature1.api.Feature1Navigation

    @AssistedFactory
    interface Feature1NavigationAssistedFactory: Feature1NavigationFactory {
        override fun create(
            @Assisted("context") context: Context,
        ): Feature1NavigationImpl
    }


    @Binds
    abstract fun bindFeature1NavFactory(impl: Feature1NavigationAssistedFactory): Feature1NavigationFactory
//    @AssistedFactory
//    interface DepositsDetailsViewModelAssistedFactory {
//        fun create(
//            @Assisted("depositId") depositId: Long,
//            @Assisted("isCurrencyDeposit") isCurrencyDeposit: Boolean,
//        ): DepositDetailsViewModelFactory
//    }
//
//    abstract fun bindDepositDetailsViewModelFactory(viewModel: DepositDetailsViewModelFactory)
//            : ViewModelProvider<DepositDetailsContract.ViewModel>
}