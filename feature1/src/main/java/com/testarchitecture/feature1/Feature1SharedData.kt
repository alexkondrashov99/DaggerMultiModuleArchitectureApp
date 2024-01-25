package com.testarchitecture.feature1

import android.util.Log

class Feature1SharedData {
    companion object {
        const val STATIC_SHARED_DATA1 = "STATIC_SasdasdasadadadsHARED_DATA1"
    }
    fun getSharedData1(): String {
        println()
        println()
        Log.d("ALESHA", "some log")
        return "sd1"
    }
    fun getSharedData2() = "sad2"
    fun getSharedData3() = "sd3"
    fun getSharedData4() = "sd4"
}