package com.testarchitecture.feature2.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.testarchitecture.core.SomeDataProvider
import com.testarchitecture.core.Utils
import com.testarchitecture.feature1.api.Feature1Navigation
import com.testarchitecture.feature1.api.Feature1NavigationFactory
import com.testarchitecture.feature2.domain.data.Feature2Repository
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

class Feature2Activity : DaggerAppCompatActivity() {


    @Inject
    lateinit var someRepository: Feature2Repository

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
    lateinit var feature1NavFactory: Feature1NavigationFactory

    private var feature1Navigation: Feature1Navigation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature2)

        feature1Navigation = feature1NavFactory.create(this@Feature2Activity)

        observeToken()

        btFeature1.setOnClickListener {
            feature1Navigation?.navigateFeature1()
        }

        tvActivity.apply {
            setOnClickListener {
                Utils.showPopup(tvActivity, this@Feature2Activity.javaClass.name)
            }
            text = this@Feature2Activity.javaClass.simpleName
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
        feature1Navigation = null
    }
}