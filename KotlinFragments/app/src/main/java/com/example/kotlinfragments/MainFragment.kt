package com.example.kotlinfragments

import android.app.Activity
import android.app.Person
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class MainFragment : Fragment(R.layout.fragment_main) {

    private var selectedIndex: Int = 0
    lateinit var listaCast: ArrayList<Personaje>

    //Texto
    private lateinit var txtNombre: TextView

    //Image Views
    private lateinit var imgMain: ImageView
    private lateinit var btnLeft: ImageView
    private lateinit var btnRight: ImageView
    private lateinit var imgSonidoMain: ImageView

    //Botones
    private lateinit var btnInfo: Button

    //Array

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        txtNombre = view.findViewById(R.id.txtNombreMain)
        imgMain = view.findViewById(R.id.imgMain)
        btnLeft = view.findViewById(R.id.btnLeft)
        btnRight = view.findViewById(R.id.btnRight)
        btnInfo = view.findViewById(R.id.btnInfo)
        imgSonidoMain = view.findViewById(R.id.imgSonidoMain)

        listaCast = Personaje.cast

        setViews()
        setEvents()
    }

    private fun setViews() {
        txtNombre.text = listaCast.elementAt(selectedIndex).nombre
        imgMain.setImageResource(listaCast.elementAt(selectedIndex).imagen!!)
    }

    private fun setEvents() {
        btnRight.setOnClickListener {
            if(selectedIndex < (listaCast.size - 1)) {
                selectedIndex++
            } else if (selectedIndex >= (listaCast.size - 1)) {
                selectedIndex = 0
            }
            setViews()
            println(selectedIndex)
        }

        btnLeft.setOnClickListener {
            if (selectedIndex > 0) {
                selectedIndex--
            } else if (selectedIndex <= 0) {
                selectedIndex = listaCast.lastIndex
            }
            setViews()
        }

        btnInfo.setOnClickListener {
            (activity as MainActivity?)?.replaceFragment(InfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("selectedPersonaje", listaCast.elementAt(selectedIndex))
                }
            })
        }
    }
}