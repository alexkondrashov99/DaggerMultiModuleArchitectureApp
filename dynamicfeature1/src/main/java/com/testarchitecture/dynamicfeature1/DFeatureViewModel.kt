package com.testarchitecture.dynamicfeature1

import androidx.lifecycle.ViewModel
import com.testarchitecture.dynamicfeature1.di.DFeatureComponent

class DFeatureViewModel : ViewModel() {
    // Ініціалізація компонента при створенні ViewModel
    var dFeatureComponent: DFeatureComponent? = null


}