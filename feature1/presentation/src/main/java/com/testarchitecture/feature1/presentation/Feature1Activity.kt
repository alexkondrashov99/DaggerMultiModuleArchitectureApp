package com.testarchitecture.feature1.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.testarchitecture.core.SomeDataProvider
import com.testarchitecture.core.Utils
import com.testarchitecture.feature1.domain.data.Feature1Repository
import com.testarchitecture.feature2.api.Feature2Navigation
import com.testarchitecture.feature2.api.Feature2NavigationFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

class Feature1Activity : DaggerAppCompatActivity() {


    @Inject
    lateinit var someRepository: Feature1Repository

    @Inject
    lateinit var dataProvider: SomeDataProvider

    private val btFeature1
        get() = findViewById<Button>(R.id.bt_feature_1)

    private val btNewToken
        get() = findViewById<Button>(R.id.btn_new_token)

    private val tvSharedData
        get() = findViewById<TextView>(R.id.tv_some_shared_data)

    private val tvActivity
        get() = findViewById<TextView>(R.id.tv_activity)

    private val tvInjectedId
        get() = findViewById<TextView>(R.id.tv_injected_object_id)

    val viewModel by lazy {
        ViewModelProvider(this).get(FeatureViewModel::class.java)
    }



    @Inject
    lateinit var feature2NavFactory: Feature2NavigationFactory

    private var feature2Navigation: Feature2Navigation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature1)

        feature2Navigation = feature2NavFactory.create(this@Feature1Activity)

        observeToken()

        btFeature1.setOnClickListener {
            feature2Navigation?.navigateFeature2()
        }

        tvActivity.apply {
            setOnClickListener {
                Utils.showPopup(tvActivity, this@Feature1Activity.javaClass.name)
            }
            text = this@Feature1Activity.javaClass.simpleName
        }

        tvInjectedId.apply {
            setOnClickListener {
                Utils.showPopup(tvInjectedId, dataProvider.toString())
            }
            text = "@" + dataProvider.toString().substringAfter("@")
        }

        btNewToken.setOnClickListener {
            dataProvider.setToken(UUID.randomUUID().toString().take(8))
        }
    }

    private fun observeToken() {
        dataProvider.observeToken().onEach { token ->
            tvSharedData.text = token
        }.launchIn(lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        feature2Navigation = null
    }
}