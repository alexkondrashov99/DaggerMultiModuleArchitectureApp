package com.testarchitecture.dynamicfeature1.data.implementation

import com.testarchitecture.dynamicfeature1.data.interfaces.DFeature1Singletone

class DFeature1SingletoneImpl: DFeature1Singletone {
    override fun getSomeSingletoneData(): String {
        return "DFeature1SingletoneImpl $this"
    }
}