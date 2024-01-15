package com.testarchitecture.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.testarchitecture.core.DaggerAndroidActivity
import com.testarchitecture.core.SomeDataProvider
import com.testarchitecture.core.coreComponent
import com.testarchitecture.dashboard.di.DaggerDashboardComponent
import com.testarchitecture.dashboard.di.DashboardModule
import com.testarchitecture.dashboard.dynamicfeature.DynamicFeatureModule
import javax.inject.Inject

class MainActivity : DaggerAndroidActivity() {


    @Inject
    lateinit var dataProvider: SomeDataProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.bt_feature_1).setOnClickListener {
            val intent = Intent().apply {
                setClassName(this@MainActivity, DynamicFeatureModule.Feature1.entryPoint)
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.bt_feature_2).setOnClickListener {
            val intent = Intent().apply {
                setClassName(this@MainActivity, DynamicFeatureModule.Feature2.entryPoint)
            }
            startActivity(intent)
        }

        Log.d("ALESHA", "dataProvider ${dataProvider} ${dataProvider.provideToken()}")
    }

    override fun onInject() {
        DaggerDashboardComponent.factory()
            .create(coreComponent(), DashboardModule(), application).inject(this@MainActivity)
    }
}