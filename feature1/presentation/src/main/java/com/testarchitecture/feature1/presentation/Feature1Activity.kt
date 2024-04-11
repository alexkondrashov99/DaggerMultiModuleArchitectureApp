package com.testarchitecture.feature1.presentation

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.testarchitecture.feature1.domain.data.Feature1Repository
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
            }
        }

        Log.d("ALESHA", "Feature1Activity dataProvider ${dataProvider} ${dataProvider.getToken()}")
        Log.d("ALESHA", "Feature1Activity someRepository ${someRepository.getSomeData()}")
        Log.d("ALESHAVM", "Feature1Activity viewModel ${viewModel}")
        Log.d("ALESHAVM", "test log")
    }
}