package com.example.appalumno.core.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.example.appalumno.R

@BindingAdapter("loadFromUrl")
fun ImageView.loadFromURL(url: String) = this.load(url){
    crossfade(true)
    placeholder(R.drawable.img)
    transformations(CircleCropTransformation())
}

@BindingAdapter("loadFromUrlSQR")
fun ImageView.loadFromURLSQR(url: String) = this.load(url){
    crossfade(true)
    placeholder(R.drawable.img)
}