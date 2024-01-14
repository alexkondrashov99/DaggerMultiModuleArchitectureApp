package com.testarchitecture.dynamicfeature1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.testarchitecture.dynamicfeature1.data.interfaces.DFeature1Repository
import com.testarchitecture.dynamicfeature1.data.interfaces.DFeature1Singletone
import com.testarchitecture.dynamicfeature1.di.DFeatureModule
import com.testarchitecture.dynamicfeature1.di.DaggerDFeatureComponent
import com.testarchitecture.dynamicfeatureapp.DaggerAndroidActivity
import com.testarchitecture.dynamicfeatureapp.SomeDataProvider
import com.testarchitecture.dynamicfeatureapp.appComponent
import javax.inject.Inject

class Feature1AdditionalActivity : DaggerAndroidActivity() {

    @Inject
    lateinit var dataProvider: SomeDataProvider

    @Inject
    lateinit var someRepository: DFeature1Repository

    @Inject
    lateinit var someSingletone: DFeature1Singletone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature1_additional)


        Log.d("ALESHA", "Feature1AdditionalActivity dataProvider ${dataProvider} ${dataProvider.provideToken()}")
        Log.d("ALESHA", "Feature1AdditionalActivity someRepository ${someRepository.getSomeData()}")
        Log.d("ALESHA", "Feature1AdditionalActivity someSingletone ${someSingletone.getSomeSingletoneData()}")
    }

    override fun onInject() {
        DFeature1ComponentProvider.dFeatureComponent?.inject(this)
            ?: kotlin.run {
                DaggerDFeatureComponent.factory()
                    .create(appComponent(), DFeatureModule(), application).also { component ->
                        DFeature1ComponentProvider.dFeatureComponent = component
                    }.inject(this@Feature1AdditionalActivity)
            }
    }
}