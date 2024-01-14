package com.testarchitecture.dynamicfeature1

import android.content.Intent
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
            setOnClickListener {
                startActivity(Intent(this@Feature1Activity, Feature1AdditionalActivity::class.java))
            }
        }

        Log.d("ALESHA", "Feature1Activity dataProvider ${dataProvider} ${dataProvider.provideToken()}")
        Log.d("ALESHA", "Feature1Activity someRepository ${someRepository.getSomeData()}")
        Log.d("ALESHA", "Feature1Activity someSingletone ${someSingletone.getSomeSingletoneData()}")
        Log.d("ALESHAVM", "Feature1Activity viewModel ${viewModel}")
        Log.d("ALESHAVM", "Feature1Activity DFeature1ComponentProvider ${DFeature1ComponentProvider}")
    }

    override fun onInject() {
        DFeature1ComponentProvider.dFeatureComponent?.inject(this)
            ?: kotlin.run {
                DaggerDFeatureComponent.factory()
                    .create(appComponent(), DFeatureModule(), application).also { component ->
                        DFeature1ComponentProvider.dFeatureComponent = component
                    }.inject(this@Feature1Activity)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            DFeature1ComponentProvider.dFeatureComponent = null
        }
    }
}