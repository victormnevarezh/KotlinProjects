package com.example.examen1erperiodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class DetailFragment : Fragment() {

    //Array
    private lateinit var listUsers: Array<User>
    private lateinit var listArticles: Array<Article>
    private lateinit var listArticlesInts: Array<Int>

    //Index
    private var indexUser: Int = 10
    private var indexArticle: Int = 0

    //Auxiliar
    private lateinit var currentArticle: Article

    //Edit Text
    private lateinit var txtTitleDetail: EditText
    private lateinit var txtContentDetail: EditText

    //Text View
    private lateinit var txtNoHeart: TextView

    //Image View
    private lateinit var imgHeartDetail: ImageView
    private lateinit var imgArticleDetail: ImageView

    //Button
    private lateinit var btnEditDetail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        println("Inicia onCreate")
        //Recibe los argumentos
        listUsers = requireArguments().getParcelableArray("listUsersSend") as Array<User>
        listArticles = requireArguments().getParcelableArray("listArticlesSend") as Array<Article>
        indexArticle = requireArguments().getInt("indexArticleSend")

        indexUser = listUsers.indexOfFirst { it.loged }
        listArticlesInts = listUsers.elementAt(indexUser).articles
        currentArticle = listArticles.elementAt(indexArticle)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("Inicia onViewCreated")
        //Edit Text
        txtTitleDetail = view.findViewById(R.id.txtTitleDetail)
        txtContentDetail = view.findViewById(R.id.txtContentDetail)

        //Text View
        txtNoHeart = view.findViewById(R.id.txtNoHeart)

        //Image View
        imgHeartDetail = view.findViewById(R.id.imgHeartDetail)
        imgArticleDetail = view.findViewById(R.id.imgArticleDetail)

        //Button
        btnEditDetail = view.findViewById(R.id.btnEditDetail)

        initViewValues()
    }

    private fun initViewValues() {
        println("Inicia initViewValues")
        if (listUsers.elementAt(indexUser).type) {
            //En caso de que sea escritor
            setHeartResource() //Asigna imagen de corazón
            getHeartNumber() //Asigna el numero de likes
            imgArticleDetail.setImageResource(currentArticle.image)
            txtTitleDetail.setText(currentArticle.name)
            txtContentDetail.setText(currentArticle.content)
        }
        else {
            //En caso de que sea lector
            setHeartResource() //Asigna imagen de corazón
            getHeartNumber() //Asigna el numero de likes
            imgArticleDetail.setImageResource(currentArticle.image)
            txtTitleDetail.setText(currentArticle.name)
            txtContentDetail.setText(currentArticle.content)
            txtTitleDetail.isEnabled = false
            txtContentDetail.isEnabled = false
            btnEditDetail.visibility = View.GONE
        }
    }

    private fun setHeartResource() {
        var setHeart = false
        listArticlesInts.forEach {
            if (it == indexArticle) {
                setHeart = true
            }
        }

        if (setHeart) {
            imgHeartDetail.setImageResource(R.drawable.hearton)
        } else {
            imgHeartDetail.setImageResource(R.drawable.heartoff)
        }
    }

    private fun getHeartNumber() {
        var heartCounter = 0
        listUsers.forEach {
            var listAuxArticleInts = it.articles
            listAuxArticleInts.forEach {
                if (it == indexArticle) {
                    heartCounter++
                }
            }
        }
        txtNoHeart.text = "$heartCounter!"
    }
}