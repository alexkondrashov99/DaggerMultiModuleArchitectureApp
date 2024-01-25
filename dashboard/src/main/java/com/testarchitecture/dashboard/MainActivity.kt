package com.testarchitecture.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.testarchitecture.core.DaggerAndroidActivity
import com.testarchitecture.core.SomeDataProvider
import com.testarchitecture.core.coreComponent
import com.testarchitecture.dashboard.di.DashboardModule
import com.testarchitecture.dashboard.dynamicfeature.DynamicFeatureModule
import com.testarchitecture.feature1.Feature1Activity
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var dataProvider: SomeDataProvider


    @Inject
    lateinit var someString: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.bt_feature_1).setOnClickListener {
            val intent = Intent(this, Feature1Activity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.bt_feature_2).setOnClickListener {
            val intent = Intent().apply {
                setClassName(this@MainActivity, DynamicFeatureModule.Feature2.entryPoint)
            }
            startActivity(intent)
        }

        Log.d("ALESHA", "dataProvider ${dataProvider} ${dataProvider.provideToken()}")
        Log.d("ALESHA", "someString ${someString}")
    }
//
//    override fun onInject() {
//        AndroidInjection.inject(this)
////        DaggerDashboardComponent.factory()
////            .create(coreComponent(), DashboardModule(), application).inject(this@MainActivity)
//    }
}