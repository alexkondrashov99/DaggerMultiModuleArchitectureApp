package com.testarchitecture.dashboard

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.testarchitecture.core.SomeDataProvider
import com.testarchitecture.core.Utils
import com.testarchitecture.feature1.api.Feature1Navigation
import com.testarchitecture.mylibrary.DynamicFeatureNavigation
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

class DashboardActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var dataProvider: SomeDataProvider

    private val btFeature1
        get() = findViewById<Button>(R.id.bt_feature_1)

    private val btFeature2
        get() = findViewById<Button>(R.id.bt_feature_2)

    private val btDynamicFeature1
        get() = findViewById<Button>(R.id.bt_dynamic_feature_1)

    private val btNewToken
        get() = findViewById<Button>(R.id.btn_new_token)

    private val tvSharedData
        get() = findViewById<TextView>(R.id.tv_some_shared_data)

    private val tvActivity
        get() = findViewById<TextView>(R.id.tv_activity)

    private val tvInjectedId
        get() = findViewById<TextView>(R.id.tv_injected_object_id)

    /**
     * Feature1 Navigation
     */
    @Inject
    lateinit var feature1Navigation: Feature1Navigation


    /**
     * Feature2 Navigation
     */
    @Inject
    lateinit var feature2Navigation: Feature1Navigation


    /**
     * DynamicFeature1 Navigation
     */
    @Inject
    lateinit var dynamicFeatureNavigation: DynamicFeatureNavigation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        observeToken()

        btFeature1.setOnClickListener {
            feature1Navigation.navigateFeature1(this@DashboardActivity)
        }

        btFeature2.setOnClickListener {
            feature2Navigation.navigateFeature1(this@DashboardActivity)
        }

        btDynamicFeature1.setOnClickListener {
            dynamicFeatureNavigation.navigateDynamicFeature1(this)
        }

        tvActivity.apply {
            setOnClickListener {
                Utils.showPopup(tvActivity, this@DashboardActivity.javaClass.name)
            }
            text = this@DashboardActivity.javaClass.simpleName
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
}