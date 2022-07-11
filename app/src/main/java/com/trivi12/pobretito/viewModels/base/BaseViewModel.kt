package com.trivi12.pobretito.viewModels.base

import android.content.SharedPreferences
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

abstract class BaseViewModel(val sharedPreferences:SharedPreferences):ViewModel() {
    abstract fun removeObserver(lifecycleOwner: LifecycleOwner)
}