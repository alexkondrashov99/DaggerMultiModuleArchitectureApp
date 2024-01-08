package com.testarchitecture.dynamicfeature1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.testarchitecture.dynamicfeatureapp.SharedDataObject
import dagger.android.support.DaggerAppCompatActivity

class Feature1Activity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature1)

        findViewById<TextView>(R.id.tv_shared_data).apply {
            setText(Feature1Activity::class.java.toString())
        }
    }
}