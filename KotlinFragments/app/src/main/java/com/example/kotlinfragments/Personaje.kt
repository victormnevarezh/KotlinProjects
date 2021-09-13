package com.example.kotlinfragments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Personaje (
    var nombre: String = "",
    var frase: String = "",
    var imagen: Int? = null,
    var audio: Int? = null,
    var favPersonaje: Boolean = false,
    var favSonido: Boolean = false
) : Parcelable {

    companion object {
        val cast = arrayListOf<Personaje>(
            Personaje(Cast.TULIO.nombre, Cast.TULIO.frase, Cast.TULIO.imagen, Cast.TULIO.audio),
            Personaje(Cast.BODOQUE.nombre, Cast.BODOQUE.frase, Cast.BODOQUE.imagen, Cast.BODOQUE.audio),
            Personaje(Cast.JUANIN.nombre, Cast.JUANIN.frase, Cast.JUANIN.imagen, Cast.JUANIN.audio),
            Personaje(Cast.MARIOHUGO.nombre, Cast.MARIOHUGO.frase, Cast.MARIOHUGO.imagen, Cast.MARIOHUGO.audio)
        )
    }
}