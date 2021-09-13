package com.example.kotlinfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class ImageFragment : Fragment(R.layout.fragment_image) {

    //Image View
    private lateinit var imageZoomed: ImageView

    //Imagen
    private var selectedImage: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageZoomed = view.findViewById(R.id.imageZoomed)
        setImage()
    }

    private fun setImage() {
        selectedImage = requireArguments().getInt("selectedImage")
        imageZoomed.setImageResource(selectedImage)
    }
}