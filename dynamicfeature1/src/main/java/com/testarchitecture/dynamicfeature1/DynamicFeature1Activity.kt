package com.testarchitecture.dynamicfeature1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.play.core.splitcompat.SplitCompat
import com.testarchitecture.core.DaggerAndroidActivity
import com.testarchitecture.core.Utils
import com.testarchitecture.core.coreComponent
import com.testarchitecture.dynamicfeature1.data.interfaces.DFeature1Repository
import com.testarchitecture.dynamicfeature1.di.DFeatureModule
import com.testarchitecture.dynamicfeature1.di.DaggerDFeatureComponent
import com.testarchitecture.feature1.api.Feature1Navigation
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

class DynamicFeature1Activity : DaggerAndroidActivity() {

    @Inject
    lateinit var dataProvider: com.testarchitecture.core.SomeDataProvider

    @Inject
    lateinit var someRepository: DFeature1Repository

    @Inject
    lateinit var feature1Navigation: Feature1Navigation

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
        ViewModelProvider(this).get(DFeatureViewModel::class.java)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_feature1)

        tvActivity.apply {
            setOnClickListener {
                Utils.showPopup(tvActivity, this@DynamicFeature1Activity.javaClass.name)
            }
            text = this@DynamicFeature1Activity.javaClass.simpleName
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

        btFeature1.setOnClickListener {
            feature1Navigation.navigateFeature1(this@DynamicFeature1Activity)
        }

        observeToken()
    }

    override fun onInject() {
        DaggerDFeatureComponent.factory()
            .create(coreComponent(), DFeatureModule(), application).inject(this@DynamicFeature1Activity)
    }

    private fun observeToken() {
        dataProvider.observeToken().onEach { token ->
            tvSharedData.text = token
        }.launchIn(lifecycleScope)
    }
}