package com.testarchitecture.dynamicfeature1.data.implementation

import com.testarchitecture.dynamicfeature1.data.interfaces.DFeature1Repository

class DFeature1RepositoryImpl: DFeature1Repository {
    override fun getSomeData(): String {
        return "DFeature1RepositoryImpl $this"
    }

}