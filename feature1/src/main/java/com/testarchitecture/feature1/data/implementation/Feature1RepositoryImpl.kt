package com.testarchitecture.feature1.data.implementation

import com.testarchitecture.feature1.data.interfaces.Feature1Repository

class Feature1RepositoryImpl: Feature1Repository {
    override fun getSomeData(): String {
        return "Feature1RepositoryImpl $this"
    }

}