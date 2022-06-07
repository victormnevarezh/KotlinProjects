package com.example.appmaestro.core.presentation

import com.example.appmaestro.core.exception.Failure

interface OnFailure {
    fun handleFailure(failure: Failure?)
}