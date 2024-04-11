package com.testarchitecture.feature1.data

import com.testarchitecture.feature1.domain.data.Feature1Repository

class Feature1RepositoryImpl: Feature1Repository {
    override fun getSomeData(): String {
        return "Feature1RepositoryImpl $this some data 1234"
    }
}