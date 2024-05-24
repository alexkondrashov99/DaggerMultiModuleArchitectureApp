package com.testarchitecture.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.testarchitecture.core.SomeDataProvider
import com.testarchitecture.core.Utils
import com.testarchitecture.core.dynamicfeature.DynamicFeatureHandler
import com.testarchitecture.core.dynamicfeature.DynamicFeatureInstallError
import com.testarchitecture.core.dynamicfeature.DynamicFeatureLauncher
import com.testarchitecture.core.dynamicfeature.DynamicFeatureModule
import com.testarchitecture.feature1.api.Feature1Navigation
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

class DashboardActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var dataProvider: SomeDataProvider

    private val progressBar
        get() = findViewById<ProgressBar>(R.id.pb_progress)

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
            navigateDynamicFeature()
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

    private fun navigateDynamicFeature() {
        val launcher = DynamicFeatureLauncher(DynamicFeatureModule.Feature1)
        val handler = object : DynamicFeatureHandler {
            override fun showError(error: DynamicFeatureInstallError) {
                Toast.makeText(this@DashboardActivity, error.errorMessage, Toast.LENGTH_LONG).show()
            }

            override fun getContext(): Context {
                return this@DashboardActivity
            }

            override fun showProgress(shouldShowProgress: Boolean) {
                progressBar.isVisible = shouldShowProgress
            }

            override fun getDynamicFeatureResultLauncher(): ActivityResultLauncher<IntentSenderRequest> {
                return  resultLauncher
            }

        }
        launcher.initializeLaunch(handler)
    }

    private fun observeToken() {
        dataProvider.observeToken().onEach { token ->
            tvSharedData.text = token
        }.launchIn(lifecycleScope)
    }

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        Log.d("ALESHA", "Dashboard result")
    }
}