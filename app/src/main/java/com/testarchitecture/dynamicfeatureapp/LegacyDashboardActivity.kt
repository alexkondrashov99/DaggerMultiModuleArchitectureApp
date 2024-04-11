package com.testarchitecture.dynamicfeatureapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.testarchitecture.core.SomeDataProvider
import com.testarchitecture.core.Utils
import com.testarchitecture.dashboard.dynamicfeature.DynamicFeatureModule
import com.testarchitecture.feature1.presentation.Feature1Activity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.w3c.dom.Text
import java.util.UUID
import javax.inject.Inject

class LegacyDashboardActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var dataProvider: SomeDataProvider

    private val btFeature1
        get() = findViewById<Button>(R.id.bt_feature_1)

    private val btFeature2
        get() = findViewById<Button>(R.id.bt_feature_2)

    private val btFeature3
        get() = findViewById<Button>(R.id.bt_feature_3)

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
            val intent = Intent().apply {
                setClassName(this@LegacyDashboardActivity, DynamicFeatureModule.Feature1.entryPoint)
            }
            startActivity(intent)
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

    private fun observeToken() {
        dataProvider.observeToken().onEach { token ->
            tvSharedData.text = token
        }.launchIn(lifecycleScope)
    }
}