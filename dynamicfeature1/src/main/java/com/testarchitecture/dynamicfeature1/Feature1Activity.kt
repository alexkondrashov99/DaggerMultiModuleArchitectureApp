package com.testarchitecture.dynamicfeature1

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.testarchitecture.dynamicfeature1.data.interfaces.DFeature1Repository
import com.testarchitecture.dynamicfeature1.data.interfaces.DFeature1Singletone
import com.testarchitecture.dynamicfeature1.di.DFeatureModule
import com.testarchitecture.dynamicfeature1.di.DaggerDFeatureComponent
import com.testarchitecture.dynamicfeatureapp.AppApplication
import com.testarchitecture.dynamicfeatureapp.DaggerAndroidActivity
import com.testarchitecture.dynamicfeatureapp.SomeDataProvider
import com.testarchitecture.dynamicfeatureapp.appComponent
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class Feature1Activity : DaggerAndroidActivity() {

    @Inject
    lateinit var dataProvider: SomeDataProvider

    @Inject
    lateinit var someRepository: DFeature1Repository

    @Inject
    lateinit var someSingletone: DFeature1Singletone

    val viewModel by lazy {
        ViewModelProvider(this).get(DFeatureViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature1)

        findViewById<TextView>(R.id.tv_shared_data).apply {
            setText(Feature1Activity::class.java.toString())
        }

        Log.d("ALESHA", "dataProvider ${dataProvider} ${dataProvider.provideToken()}")
        Log.d("ALESHA", "someRepository ${someRepository.getSomeData()}")
        Log.d("ALESHA", "someSingletone ${someSingletone.getSomeSingletoneData()}")
        Log.d("ALESHAVM", "viewModel ${viewModel}")
    }

    override fun onInject() {
        viewModel.dFeatureComponent?.inject(this)
            ?: kotlin.run {
                DaggerDFeatureComponent.factory()
                    .create(appComponent(), DFeatureModule(), application).also { component ->
                        viewModel.dFeatureComponent = component
                    }.inject(this@Feature1Activity)
            }
    }
}