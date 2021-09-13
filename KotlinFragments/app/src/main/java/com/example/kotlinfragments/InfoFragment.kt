package com.example.kotlinfragments

import android.media.MediaActionSound
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class InfoFragment : Fragment(R.layout.fragment_info) {

    //Index
    private var selectedIndex: Int = 0

    //Array
    private lateinit var listaCast: Array<Personaje>

    //Text View
    private lateinit var txtNombreInfo: TextView
    private lateinit var txtFrase: TextView

    //Image View
    private lateinit var imgInfo: ImageView
    private lateinit var imgStar: ImageView
    private lateinit var imgSoundInfo: ImageView
    private lateinit var imgFrase: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        txtNombreInfo = view.findViewById(R.id.txtNombreInfo)
        txtFrase = view.findViewById(R.id.txtFrase)
        imgInfo = view.findViewById(R.id.imgInfo)
        imgStar = view.findViewById(R.id.imgStar)
        imgSoundInfo = view.findViewById(R.id.imgSoundInfo)
        imgFrase = view.findViewById(R.id.imgFrase)

        arguments()
        setViews()
        setStarImage()
        setAudioImage()
        setEvents()
    }

    private fun arguments() {
        listaCast = requireArguments().getParcelableArray("listaCast") as Array<Personaje>
        selectedIndex = requireArguments().getInt("selectedIndex")
    }

    private fun setViews() {
        txtNombreInfo.text = listaCast.elementAt(selectedIndex).nombre
        txtFrase.text = listaCast.elementAt(selectedIndex).frase
        imgInfo.setImageResource(listaCast.elementAt(selectedIndex).imagen!!)
    }

    private fun setEvents() {
        imgFrase.setOnClickListener{
            MediaPlayer.create(context, listaCast.elementAt(selectedIndex).audio).start()
        }
        imgInfo.setOnClickListener{
            (activity as MainActivity?)?.replaceFragment(ImageFragment().apply {
                arguments = Bundle().apply {
                    putInt("selectedImage", listaCast.elementAt(selectedIndex).imagen)
                }
            })
        }

        imgStar.setOnClickListener{
            when (listaCast.elementAt(selectedIndex).favPersonaje) {
                true -> { listaCast.elementAt(selectedIndex).favPersonaje = false }
                false -> { listaCast.elementAt(selectedIndex).favPersonaje = true }
            }
            setStarImage()
        }

        imgSoundInfo.setOnClickListener{
            listaCast.forEach {
                if (it.favSonido) {
                    it.favSonido = false
                }
            }
            listaCast.elementAt(selectedIndex).favSonido = true
            setAudioImage()
        }

    }

    private fun setStarImage() {
        when (listaCast.elementAt(selectedIndex).favPersonaje) {
            true -> { imgStar.setImageResource(R.drawable.staron) }
            false -> { imgStar.setImageResource(R.drawable.staroff) }
        }
    }

    private fun setAudioImage() {
        when (listaCast.elementAt(selectedIndex).favSonido) {
            true -> { imgSoundInfo.setImageResource(R.drawable.soundon) }
            false -> { imgSoundInfo.setImageResource(R.drawable.soundoff) }
        }
    }

}