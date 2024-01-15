package com.testarchitecture.dashboard.dynamicfeature

import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import java.lang.RuntimeException

class DynamicFeatureLauncher(
    private val handler: DynamicFeatureHandler,
    private val module: DynamicFeatureModule
) {

    private var mySessionId: Int = -1
    private var manager: SplitInstallManager? = null

    private var splitInstallListener =
        SplitInstallStateUpdatedListener { state ->
            if (state.sessionId() == mySessionId)
                when (state.status()) {
                    SplitInstallSessionStatus.INSTALLED -> {
                        handler.showProgress(false)
                        startDynamicModuleComponent(module.entryPoint)
                        clearSubscription()
                    }
                    SplitInstallSessionStatus.FAILED -> {
                        handler.showProgress(false)
                        handler.showError(RuntimeException("Failed to install ${module.name} with error ${state.errorCode()}"))
                        clearSubscription()
                    }
                    SplitInstallSessionStatus.CANCELED -> {
                        handler.showProgress(false)
                        clearSubscription()
                    }
                    else -> Unit
                }
        }

    fun initializeLaunch() {
        manager = SplitInstallManagerFactory
            .create(handler.getContext())
            .also { splitManager ->
                if (!splitManager.installedModules.contains(module.name)) {

                    splitManager.registerListener(splitInstallListener)
                    val splitInstallRequest = SplitInstallRequest.newBuilder()
                        .addModule(module.name)
                        .build()
                    splitManager.startInstall(splitInstallRequest)
                        .addOnSuccessListener { result ->
                            mySessionId = result
                            handler.showProgress(true)
                        }
                        .addOnFailureListener { error ->
                            handler.showError(error)
                            clearSubscription()
                        }

                } else {
                    startDynamicModuleComponent(module.entryPoint)
                }
            }
    }


    private fun startDynamicModuleComponent(componentPath: String) {
        handler.navigateFeature(componentPath)
    }

    private fun clearSubscription() {
        manager?.unregisterListener(splitInstallListener)
    }
}