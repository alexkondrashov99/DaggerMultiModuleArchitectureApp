package com.testarchitecture.dynamicfeature2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.testarchitecture.dynamicfeature1.Feature1SharedData

class Feature2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature2)

        findViewById<TextView>(R.id.tv_feature1_data).apply {
            setText(Feature1SharedData.STATIC_SHARED_DATA1)
        }


    }
}