package com.example.examen1erperiodo

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass (generateAdapter = true)
@Parcelize
class User (
    var username: String,
    var password: String,
    var name: String,
    var type: Boolean,
    var image: Int,
    var articles: Array<Int>,
    var loged: Boolean = false
        ) : Parcelable {

    companion object {
        val users = arrayOf<User>(
            User("tulio", "yo", "Tulio Triviño", true, R.drawable.tulio, arrayOf(0,1)),
            User("bodoque", "123", "Juan Carlos Bodoque", true, R.drawable.bodoque, arrayOf(2,3,4)),
            User("mariohugo", "123", "Ernesto Felipe Mario Hugo", true,  R.drawable.mariohugo, arrayOf(5)),
            User("guaripolo", "123", "Guaripolo", false, R.drawable.guaripolo, arrayOf(0,1,2)),
            User("joepino", "123", "Joe Pino", false, R.drawable.joepino, arrayOf(2,3,4)),
            User("juanin", "123", "Juanín Juan Harry", false, R.drawable.juanin, arrayOf(3,4,5))
        )
    }

    fun validateUser() = users.firstOrNull { (it.username == this.username && it.password == this.password) }
}