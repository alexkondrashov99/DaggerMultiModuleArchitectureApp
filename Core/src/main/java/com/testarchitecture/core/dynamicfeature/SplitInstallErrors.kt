package com.testarchitecture.core.dynamicfeature

import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode

sealed class DynamicFeatureInstallError(val errorMessage: String) {
    object SomethingWentWrong: DynamicFeatureInstallError("something went wrong")
    object NetworkError: DynamicFeatureInstallError("network error")
    object ReturnToForeground: DynamicFeatureInstallError("return to foreground")
    object InsufficientStorage: DynamicFeatureInstallError("insufficient storage")
    object RestartApp: DynamicFeatureInstallError("restart app")
    object Retry: DynamicFeatureInstallError("Retry")
    object DownloadPlayStore: DynamicFeatureInstallError("DownloadPlayStore")

}

fun getDynamicFeatureInstallError(errorCode: Int): DynamicFeatureInstallError {
    return  when(errorCode) {
        SplitInstallErrorCode.NETWORK_ERROR -> DynamicFeatureInstallError.NetworkError

        SplitInstallErrorCode.ACCESS_DENIED -> DynamicFeatureInstallError.ReturnToForeground

        SplitInstallErrorCode.INSUFFICIENT_STORAGE -> DynamicFeatureInstallError.InsufficientStorage

        SplitInstallErrorCode.SPLITCOMPAT_EMULATION_ERROR,
        SplitInstallErrorCode.SPLITCOMPAT_COPY_ERROR,
        SplitInstallErrorCode.SPLITCOMPAT_VERIFICATION_ERROR -> DynamicFeatureInstallError.RestartApp

        SplitInstallErrorCode.INTERNAL_ERROR -> DynamicFeatureInstallError.Retry

        SplitInstallErrorCode.PLAY_STORE_NOT_FOUND -> DynamicFeatureInstallError.DownloadPlayStore

        SplitInstallErrorCode.MODULE_UNAVAILABLE,
        SplitInstallErrorCode.INVALID_REQUEST,
        SplitInstallErrorCode.SESSION_NOT_FOUND,
        SplitInstallErrorCode.API_NOT_AVAILABLE,
        SplitInstallErrorCode.INCOMPATIBLE_WITH_EXISTING_SESSION,
        SplitInstallErrorCode.SERVICE_DIED,
        SplitInstallErrorCode.APP_NOT_OWNED,
        SplitInstallErrorCode.ACTIVE_SESSIONS_LIMIT_EXCEEDED -> DynamicFeatureInstallError.SomethingWentWrong

        else -> DynamicFeatureInstallError.SomethingWentWrong
    }
}