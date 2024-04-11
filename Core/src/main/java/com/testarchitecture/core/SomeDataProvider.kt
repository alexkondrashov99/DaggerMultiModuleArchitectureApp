package com.testarchitecture.core

import kotlinx.coroutines.flow.Flow

interface SomeDataProvider {
    fun getToken(): String
    fun setToken(token: String)
    fun observeToken(): Flow<String>
}