package com.example.kotlinfragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainFragment : Fragment(R.layout.fragment_main) {

    //Index
    private var selectedIndex: Int = 0

    //Array
    private lateinit var listaCast: Array<Personaje>

    //Texto
    private lateinit var txtNombre: TextView

    //Image Views
    private lateinit var imgMain: ImageView
    private lateinit var btnLeft: ImageView
    private lateinit var btnRight: ImageView
    private lateinit var imgSonidoMain: ImageView

    //Botones
    private lateinit var btnInfo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        listaCast = requireArguments().getParcelableArray("listaSended") as Array<Personaje>

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity?)?.savePreferences(listaCast)

        txtNombre = view.findViewById(R.id.txtNombreMain)
        imgMain = view.findViewById(R.id.imgMain)
        btnLeft = view.findViewById(R.id.btnLeft)
        btnRight = view.findViewById(R.id.btnRight)
        btnInfo = view.findViewById(R.id.btnInfo)
        imgSonidoMain = view.findViewById(R.id.imgSonidoMain)

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
                    putParcelableArray("listaCast", listaCast)
                    putInt("selectedIndex", selectedIndex)
                }
            })
        }

        imgSonidoMain.setOnClickListener {
            listaCast.forEach {
                if (it.favSonido)
                    MediaPlayer.create(context, it.audio).start()
            }
        }
    }
}