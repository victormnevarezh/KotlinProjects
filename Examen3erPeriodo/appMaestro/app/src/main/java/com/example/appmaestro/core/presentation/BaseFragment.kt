package com.example.appmaestro.core.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appmaestro.core.exception.Failure

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId), OnFailure {
    val navController by lazy { findNavController() }
    val baseActivity by lazy {requireActivity() as BaseActivity}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseActivity.setUpNavigation(navController)
        setBinding(view)
    }

    abstract fun setBinding(view: View)

    open fun onViewStateChanged(state: BaseViewState?){
        when(state){
            //BaseViewState.ShowLoading -> showLoader(true)
            //BaseViewState.HideLoading -> showLoader(false)
        }
    }

    //open fun showLoader(show: Boolean)=baseActivity.showProgress(show)

    override fun handleFailure(failure: Failure?) {
        when(failure){
            is Failure.DatabaseError -> {}
            is Failure.FeatureFailure -> {}
            is Failure.NetworkConnection -> {}
            is Failure.ServerError -> {}
            is Failure.Unauthorized -> {}
            null -> {}
        }
    }
}