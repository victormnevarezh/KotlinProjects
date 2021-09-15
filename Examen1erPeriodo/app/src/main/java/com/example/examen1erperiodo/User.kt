package com.example.examen1erperiodo

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass (generateAdapter = true)
@Parcelize
class User (
    var name: String,
    var type: Boolean,
    var articles: Array<Article>,
    var loged: Boolean = false
        ) : Parcelable {

    companion object {
        val users = arrayOf<User>(
            User("Tulio Triviño", true, arrayOf(Article.publishedArticles.elementAt(0), Article.publishedArticles.elementAt(1))),
            User("Juan Carlos Bodoque", true, arrayOf(Article.publishedArticles.elementAt(2), Article.publishedArticles.elementAt(3), Article.publishedArticles.elementAt(4))),
            User("Ernesto Felipe Mario Hugo", true, arrayOf(Article.publishedArticles.elementAt(5))),
            User("Guaripolo", false, arrayOf(Article.publishedArticles.elementAt(0), Article.publishedArticles.elementAt(1), Article.publishedArticles.elementAt(2))),
            User("Joe Pino", false, arrayOf(Article.publishedArticles.elementAt(1), Article.publishedArticles.elementAt(2), Article.publishedArticles.elementAt(4))),
            User("Juanín Juan Harry", false, arrayOf(Article.publishedArticles.elementAt(3), Article.publishedArticles.elementAt(4), Article.publishedArticles.elementAt(5)))
        )
    }
}