package com.example.appalumno.core.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appalumno.core.exception.Failure

abstract class BaseViewModel :ViewModel() {
    var state: MutableLiveData<BaseViewState> = MutableLiveData()
    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure){
        this.failure.value = failure
        state.value = BaseViewState.HideLoading
    }
}