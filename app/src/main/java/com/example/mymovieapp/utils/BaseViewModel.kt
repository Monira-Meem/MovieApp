package com.example.mymovieapp.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val showLoader = MutableLiveData<Boolean>()
    val toastMessage = MutableLiveData<String>()
}