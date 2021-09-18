package com.example.examen1erperiodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class WriterFragment : Fragment(R.layout.fragment_writer) {

    //Array
    private lateinit var listUsers: Array<User>
    private lateinit var listArticles: Array<Article>
    private lateinit var listArticlesInts: Array<Int>

    //Index
    private var indexUser: Int = 10
    private var indexArticle: Int = 0

    //Auxiliar
    private lateinit var currentArticle: Article

    //Text View
    private lateinit var txtNicknameWriter: TextView
    private lateinit var txtUserTypeWriter: TextView
    private lateinit var txtNoArticles: TextView
    private lateinit var txtTitleWriter: TextView

    //Image View
    private lateinit var imgUserWriter: ImageView
    private lateinit var imgArticleWriter: ImageView
    private lateinit var imgArrowLeftWriter: ImageView
    private lateinit var imgArrowRightWriter: ImageView

    //Button
    private lateinit var btnArticleNew: Button
    private lateinit var btnArticleEdit: Button
    private lateinit var btnArticleDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        //Recibe los argumentos desde el Main Activity
        listUsers = requireArguments().getParcelableArray("listUsersSend") as Array<User>
        listArticles = requireArguments().getParcelableArray("listArticlesSend") as Array<Article>

        indexUser = listUsers.indexOfFirst { it.loged }

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Text View
        txtNicknameWriter = view.findViewById(R.id.txtNicknameWriter)
        txtUserTypeWriter = view.findViewById(R.id.txtUserTypeWriter)
        txtNoArticles = view.findViewById(R.id.txtNoArticles)
        txtTitleWriter = view.findViewById(R.id.txtTitleWriter)

        //Image View
        imgUserWriter = view.findViewById(R.id.imgUserWriter)
        imgArticleWriter = view.findViewById(R.id.imgArticleWriter)
        imgArrowLeftWriter = view.findViewById(R.id.imgArrowLeftWriter)
        imgArrowRightWriter = view.findViewById(R.id.imgArrowRightWriter)

        //Button
        btnArticleNew = view.findViewById(R.id.btnArticleNew)
        btnArticleEdit = view.findViewById(R.id.btnArticleEdit)
        btnArticleDelete = view.findViewById(R.id.btnArticleDelete)

        initViewValues()
        setEvents()
    }

    private fun initViewValues() {
        //Header
        imgUserWriter.setImageResource(listUsers.elementAt(indexUser).image)
        txtNicknameWriter.text = listUsers.elementAt(indexUser).name
        txtUserTypeWriter.text = "Escritor"
        txtNoArticles.text = listUsers.elementAt(indexUser).articles.size.toString()
        listArticlesInts = listUsers.elementAt(indexUser).articles
        articleView()
    }

    private fun articleView() {
        currentArticle = getArticleById()
        txtTitleWriter.text = currentArticle.name
        imgArticleWriter.setImageResource(currentArticle.image)
    }

    private fun getArticleById() : Article {
        var auxArticle: Article = listArticles.elementAt(listArticlesInts.elementAt(indexArticle))
        return auxArticle
    }

    private fun setEvents() {
        imgArrowRightWriter.setOnClickListener {
            if (indexArticle < (listArticlesInts.size -1)) {
                indexArticle++
            } else if (indexArticle >= (listArticlesInts.size -1)) {
                indexArticle = 0
            }
            articleView()
        }

        imgArrowLeftWriter.setOnClickListener {
            if (indexArticle > 0) {
                indexArticle--
            } else if (indexArticle <= 0) {
                indexArticle = listArticlesInts.lastIndex
            }
            articleView()
        }
    }

}