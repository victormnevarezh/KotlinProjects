package com.example.examen1erperiodo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Article (
    var name:String,
    var content: String,
    var image: Int
        ) : Parcelable {

    companion object {
        val publishedArticles = arrayOf<Article>(
            Article("Las ballenas más longevas", "Las ballenas árticas también conocidas como ballenas boreales son los cetaceos más longevos, pueden llegar a vivir hasta 200 años.", R.drawable.artic),
            Article("El porqué las belugas son tan bonitas", "Las belugas son muy bonitas porque son blancas y gorditas.", R.drawable.beluga),
            Article("El coloso del mar", "Las ballenas azules son el animal más grande del mundo, pueden llegar a medir hasta 30 metros y pesar hasta 150 toneladas.",  R.drawable.blue),
            Article("¿Por qué las ballenas jorobadas saltan fuera del agua?", "Porque les gusta jugar :)", R.drawable.humpback),
            Article("El animal sin depredador", "Las orcas no tienen depredador natural más que el humano.",  R.drawable.orca),
            Article("¿Por qué los cachalotes solamente duermen 15 minutos diarios?", "Porque tienen insomnio :(", R.drawable.spermwhale)
        )
    }

}