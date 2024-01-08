package com.testarchitecture.dynamicfeature1

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.testarchitecture.dynamicfeature1.di.DaggerDFeatureComponent
import com.testarchitecture.dynamicfeatureapp.AppApplication
import com.testarchitecture.dynamicfeatureapp.DaggerAndroidActivity
import com.testarchitecture.dynamicfeatureapp.SomeDataProvider
import com.testarchitecture.dynamicfeatureapp.appComponent
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class Feature1Activity : DaggerAndroidActivity() {

    @Inject
    lateinit var dataProvider: SomeDataProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature1)

        findViewById<TextView>(R.id.tv_shared_data).apply {
            setText(Feature1Activity::class.java.toString())
        }

        Log.d("ALESHA", "dataProvider ${dataProvider} ${dataProvider.provideToken()}")
    }

    override fun onInject() {
        DaggerDFeatureComponent.factory().create(appComponent()).inject(this@Feature1Activity)
    }
}