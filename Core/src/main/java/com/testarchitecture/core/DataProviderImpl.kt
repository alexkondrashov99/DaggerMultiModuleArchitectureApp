package com.testarchitecture.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DataProviderImpl: com.testarchitecture.core.SomeDataProvider {

    private val tokenStateFlow = MutableStateFlow("defaultToken")

    override fun getToken() = tokenStateFlow.value
    override fun setToken(token: String) {
        tokenStateFlow.tryEmit(token)
    }

    override fun observeToken(): Flow<String> = tokenStateFlow
}