package com.example.appalumno.core.presentation

import com.example.appalumno.core.exception.Failure

interface OnFailure {
    fun handleFailure(failure: Failure?)
}