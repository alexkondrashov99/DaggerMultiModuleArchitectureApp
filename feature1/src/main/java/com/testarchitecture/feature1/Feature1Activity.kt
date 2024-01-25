package com.testarchitecture.feature1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.testarchitecture.feature1.data.interfaces.Feature1Repository
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class Feature1Activity : DaggerAppCompatActivity() {

    @Inject
    lateinit var dataProvider: com.testarchitecture.core.SomeDataProvider

    @Inject
    lateinit var someRepository: Feature1Repository

    val viewModel by lazy {
        ViewModelProvider(this).get(FeatureViewModel::class.java)
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
        Log.d("ALESHAVM", "Feature1Activity viewModel ${viewModel}")
    }
}