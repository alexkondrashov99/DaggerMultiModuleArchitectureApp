package com.testarchitecture.feature2.data

import com.testarchitecture.feature2.domain.data.Feature2Repository

class Feature2RepositoryImpl: Feature2Repository {
    override fun getSomeData(): String {
        return "Feature2RepositoryImpl $this some data 1234"
    }
}