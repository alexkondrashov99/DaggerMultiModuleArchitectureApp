package com.testarchitecture.dashboard

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.testarchitecture.core.SomeDataProvider
import com.testarchitecture.feature1.api.Feature1Navigation
import com.testarchitecture.feature1.api.Feature1NavigationFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DashboardActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var dataProvider: SomeDataProvider


    @Inject
    lateinit var someString: String


    @Inject
    lateinit var feature1NavFactory: Feature1NavigationFactory

    private lateinit var feature1Navigation: Feature1Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feature1Navigation = feature1NavFactory.create(this@DashboardActivity)


        findViewById<Button>(R.id.bt_feature_1).setOnClickListener {

        }

        findViewById<Button>(R.id.bt_feature_2).setOnClickListener {

        }

        Log.d("ALESHA", "dataProvider ${dataProvider} ${dataProvider.getToken()}")
        Log.d("ALESHA", "someString ${someString}")
    }
//
//    override fun onInject() {
//        AndroidInjection.inject(this)
////        DaggerDashboardComponent.factory()
////            .create(coreComponent(), DashboardModule(), application).inject(this@MainActivity)
//    }
}