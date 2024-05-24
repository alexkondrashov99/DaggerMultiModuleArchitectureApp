package com.testarchitecture.core.dynamicfeature

import android.content.Intent
import android.util.Log
import com.google.android.play.core.splitinstall.SplitInstallException
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class DynamicFeatureLauncher(
    private val module: DynamicFeatureModule
) {
    private var handler: DynamicFeatureHandler? = null

    private var mySessionId: Int = -1
    private var manager: SplitInstallManager? = null

    
    private var splitInstallListener =
        SplitInstallStateUpdatedListener { state ->
            Log.d("ALESHA", "StateUpdated: $state" )
            if (state.sessionId() == mySessionId)
                when (state.status()) {
                    SplitInstallSessionStatus.INSTALLED -> {
                        handler?.showProgress(false)
                        startDynamicModuleComponent(module.entryPoint)
                        clearSubscription()
                    }
                    SplitInstallSessionStatus.FAILED -> {
                        handler?.showProgress(false)
                        handler?.showError(getDynamicFeatureInstallError(state.errorCode()))
                        clearSubscription()
                    }
                    SplitInstallSessionStatus.CANCELED -> {
                        handler?.showProgress(false)
                        clearSubscription()
                    }
                    SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                        handler?.let { handler ->
                            manager?.startConfirmationDialogForResult(state, handler.getDynamicFeatureResultLauncher())
                        }

                    }
                    else -> Unit
                }
        }

    fun initializeLaunch(dynamicFeatureHandler: DynamicFeatureHandler) {
        if (handler != null || manager != null) return

        this.handler = dynamicFeatureHandler

        manager = SplitInstallManagerFactory
            .create(dynamicFeatureHandler.getContext())
            .also { splitManager ->
                if (!splitManager.installedModules.contains(module.name)) {

                    splitManager.registerListener(splitInstallListener)
                    val splitInstallRequest = SplitInstallRequest.newBuilder()
                        .addModule(module.name)
                        .build()

                    splitManager.startInstall(splitInstallRequest)
                        .addOnSuccessListener { result ->
                            Log.d("ALESHA", "OnSuccessListener sessionId ${result}" )
                            mySessionId = result
                            handler?.showProgress(true)
                        }
                        .addOnFailureListener { error ->
                            Log.d("ALESHA", "addOnFailureListener ${error.message}" )

                            val installError = (error as? SplitInstallException)?.errorCode?.let { code ->
                                    getDynamicFeatureInstallError(code)
                                } ?: DynamicFeatureInstallError.SomethingWentWrong

                            handler?.showError(installError)
                            clearSubscription()
                        }

                } else {
                    startDynamicModuleComponent(module.entryPoint)
                    clearPointers()
                }
            }
    }


    private fun startDynamicModuleComponent(componentPath: String) {
        handler?.let { handler ->
            val intent = Intent().apply {
                setClassName(handler.getContext(), componentPath)
            }
            handler.getContext().startActivity(intent)
        }

    }

    private fun clearSubscription() {
        manager?.unregisterListener(splitInstallListener)
        clearPointers()
    }

    private fun clearPointers() {
        manager = null
        handler = null
    }
}