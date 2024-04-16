package com.testarchitecture.feature2.impl

import android.content.Context
import com.testarchitecture.feature2.api.Feature2NavigationFactory
import dagger.Binds
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@Module
abstract class Feature2ApiModule {
//    @Provides
//    fun provideFeature2Navigation(): Feature2Navigation = Feature2NavigationImpl()
//

    abstract fun bindFeature2Navigation(navigationImpl: Feature2NavigationImpl): com.testarchitecture.feature2.api.Feature2Navigation

    @AssistedFactory
    interface Feature2NavigationAssistedFactory: Feature2NavigationFactory {
        override fun create(
            @Assisted("context") context: Context,
        ): Feature2NavigationImpl
    }


    @Binds
    abstract fun bindFeature2NavFactory(impl: Feature2NavigationAssistedFactory): Feature2NavigationFactory
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