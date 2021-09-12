package com.example.kotlinfragments

enum class Cast (val nombre: String, val frase: String, val imagen: Int, val audio: Int) {
    TULIO("Tulio Triviño", "Bienvenidos amigos a 31 minutos.", R.drawable.tulio, R.raw.tulio),
    BODOQUE("Juan Carlos Bodoque", "Primero, no soy su amigo, segundo quien es usted y tercero que le importa.", R.drawable.bodoque, R.raw.bodoque),
    JUANIN("Juanín Juan Harry", "Tulio, estamos al aire.", R.drawable.juanin, R.raw.juanin),
    MARIOHUGO("Ernesto Felipe Mario Hugo", "Y ahora, una entrevista con el mar... ah, que bonito.", R.drawable.mariohugo, R.raw.mariohugo)
}