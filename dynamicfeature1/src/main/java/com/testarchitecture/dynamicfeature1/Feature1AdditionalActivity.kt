package com.testarchitecture.dynamicfeature1

import android.os.Bundle
import android.util.Log
import com.testarchitecture.dynamicfeature1.data.interfaces.DFeature1Repository
import com.testarchitecture.dynamicfeature1.di.DFeatureModule
import com.testarchitecture.dynamicfeature1.di.DaggerDFeatureComponent
import com.testarchitecture.core.DaggerAndroidActivity
import com.testarchitecture.core.SomeDataProvider
import com.testarchitecture.core.coreComponent
import javax.inject.Inject

class Feature1AdditionalActivity : DaggerAndroidActivity() {

    @Inject
    lateinit var dataProvider: SomeDataProvider

    @Inject
    lateinit var someRepository: DFeature1Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature1_additional)

        Log.d("ALESHA", "Feature1AdditionalActivity dataProvider ${dataProvider} ${dataProvider.provideToken()}")
        Log.d("ALESHA", "Feature1AdditionalActivity someRepository ${someRepository.getSomeData()}")
    }

    override fun onInject() {
        DaggerDFeatureComponent.factory()
            .create(coreComponent(), DFeatureModule(), application).inject(this@Feature1AdditionalActivity)
    }
}