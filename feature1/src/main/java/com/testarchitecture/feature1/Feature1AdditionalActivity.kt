package com.testarchitecture.feature1

import android.os.Bundle
import android.util.Log
import com.testarchitecture.core.SomeDataProvider
import com.testarchitecture.feature1.data.interfaces.Feature1Repository
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class Feature1AdditionalActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var dataProvider: SomeDataProvider

    @Inject
    lateinit var someRepository: Feature1Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature1_additional)

        Log.d("ALESHA", "Feature1AdditionalActivity dataProvider ${dataProvider} ${dataProvider.provideToken()}")
        Log.d("ALESHA", "Feature1AdditionalActivity someRepository ${someRepository.getSomeData()}")
    }
}