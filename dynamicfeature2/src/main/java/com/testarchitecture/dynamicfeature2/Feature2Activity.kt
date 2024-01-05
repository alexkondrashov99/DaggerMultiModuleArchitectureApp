package com.testarchitecture.dynamicfeature2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.testarchitecture.dynamicfeatureapp.SharedDataObject

class Feature2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature2)

        findViewById<TextView>(R.id.tv_shared_data).apply {
            setText(SharedDataObject.someSharedData1)
        }
    }
}