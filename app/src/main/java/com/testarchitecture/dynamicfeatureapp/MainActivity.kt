package com.testarchitecture.dynamicfeatureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.testarchitecture.dynamicfeatureapp.dynamicfeature.DynamicFeatureModule

class MainActivity : AppCompatActivity() {
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
    }
}