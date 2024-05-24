package com.testarchitecture.dynamicfeatureapp

import android.content.Context
import android.content.Intent
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
import com.testarchitecture.feature1.presentation.Feature1Activity
import com.testarchitecture.feature2.presentation.Feature2Activity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

class LegacyDashboardActivity : DaggerAppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legacy_dashboard)

        observeToken()

        btFeature1.setOnClickListener {
            val intent = Intent(this, Feature1Activity::class.java)
            startActivity(intent)
        }

        btFeature2.setOnClickListener {
            val intent = Intent(this, Feature2Activity::class.java)
            startActivity(intent)
        }

        btDynamicFeature1.setOnClickListener {
            navigateDynamicFeature()
        }

        tvActivity.apply {
            setOnClickListener {
                Utils.showPopup(tvActivity, this@LegacyDashboardActivity.javaClass.name)
            }
            text = this@LegacyDashboardActivity.javaClass.simpleName
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
                Toast.makeText(this@LegacyDashboardActivity, error.errorMessage, Toast.LENGTH_LONG).show()
            }

            override fun getContext(): Context {
                return this@LegacyDashboardActivity
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
        Log.d("ALESHA", "LegacyDashboard result")
    }
}