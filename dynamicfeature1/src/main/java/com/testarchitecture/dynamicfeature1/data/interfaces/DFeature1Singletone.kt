package com.testarchitecture.dynamicfeature1.data.interfaces

interface DFeature1Singletone {
    fun getSomeSingletoneData(): String = this.toString()
}