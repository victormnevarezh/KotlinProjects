package com.example.examen1erperiodo

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ReaderFragment : Fragment(R.layout.fragment_reader) {

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
    private lateinit var txtNicknameReader: TextView
    private lateinit var txtUserTypeReader: TextView
    private lateinit var txtNoArticlesReader: TextView
    private lateinit var txtTitleReader: TextView

    //Image View
    private lateinit var imgUserReader: ImageView
    private lateinit var imgArticleReader: ImageView
    private lateinit var imgHeartReader: ImageView
    private lateinit var imgArrowLeftReader: ImageView
    private lateinit var imgArrowRightReader: ImageView

    //Button
    private lateinit var btnLogOutReader: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        //Recibe los argumentos desde el LoginFragment
        listUsers = requireArguments().getParcelableArray("listUsersSend") as Array<User>
        listArticles = requireArguments().getParcelableArray("listArticlesSend") as Array<Article>

        indexUser = listUsers.indexOfFirst { it.loged }
        listArticlesInts = listUsers.elementAt(indexUser).articles

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Text View
        txtNicknameReader = view.findViewById(R.id.txtNicknameReader)
        txtUserTypeReader = view.findViewById(R.id.txtUserTypeReader)
        txtNoArticlesReader = view.findViewById(R.id.txtNoArticlesReader)
        txtTitleReader = view.findViewById(R.id.txtTitleReader)

        //Image View
        imgUserReader = view.findViewById(R.id.imgUserReader)
        imgArticleReader = view.findViewById(R.id.imgArticleReader)
        imgHeartReader = view.findViewById(R.id.imgHeartReader)
        imgArrowLeftReader = view.findViewById(R.id.imgArrowLeftReader)
        imgArrowRightReader = view.findViewById(R.id.imgArrowRightReader)

        //Button
        btnLogOutReader = view.findViewById(R.id.btnLogOutReader)

        initViewValues()
        setEvents()
    }

    private fun initViewValues() {
        //Header
        imgUserReader.setImageResource(listUsers.elementAt(indexUser).image)
        txtNicknameReader.text = listUsers.elementAt(indexUser).name
        txtNoArticlesReader.text = listUsers.elementAt(indexUser).articles.size.toString()
        txtUserTypeReader.text = "Reader"

        //Body
        articleView()
    }

    private fun articleView() {
        currentArticle = listArticles.elementAt(indexArticle)
        txtTitleReader.text = currentArticle.name
        imgArticleReader.setImageResource(currentArticle.image)

        var setHeart = false
        listArticlesInts.forEach {
            if (it == indexArticle) {
                setHeart = true
            }
        }

        if (setHeart) {
            imgHeartReader.setImageResource(R.drawable.hearton)
        } else {
            imgHeartReader.setImageResource(R.drawable.heartoff)
        }
    }

    private fun setEvents() {
        imgArrowRightReader.setOnClickListener {
            if (indexArticle < (listArticles.size -1)) {
                indexArticle++
            } else if (indexArticle >= (listArticles.size -1)) {
                indexArticle = 0
            }
            articleView()
        }

        imgArrowLeftReader.setOnClickListener {
            if (indexArticle > 0) {
                indexArticle--
            } else if (indexArticle <= 0) {
                indexArticle = listArticles.lastIndex
            }
            articleView()
        }

        imgHeartReader.setOnClickListener {
            var auxArray : Array<Int> = emptyArray()
            //Turn On
            var valueHeart:Int = -1
            listArticlesInts.forEach {
                if (it == indexArticle) {
                    valueHeart = it
                }
            }

            if (valueHeart != -1) {
                println("Primero: ${auxArray.size}")
                listArticlesInts.forEach {
                    if (it != valueHeart) {
                        auxArray = append(auxArray, it)
                    }
                }
                println("Segundo: ${auxArray.size}")
                listArticlesInts = auxArray
            } else {
                println("Primero: ${listArticlesInts.size}")
                listArticlesInts = append(listArticlesInts, indexArticle)
                println("Segundo: ${listArticlesInts.size}")
            }
            listUsers.elementAt(indexUser).articles = listArticlesInts
            (activity as MainActivity?)?.savePreferences(listUsers, listArticles)
            articleView()
        }

        imgArticleReader.setOnClickListener {
            (activity as MainActivity?)?.replaceFragment(DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArray("listUsersSend", listUsers)
                    putParcelableArray("listArticlesSend", listArticles)
                }
            })
        }

        btnLogOutReader.setOnClickListener {
            listUsers.forEach {
                it.loged = false
            }

            (activity as MainActivity?)?.savePreferences(listUsers, listArticles)

            (activity as MainActivity?)?.replaceFragment(LogInFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArray("listUsersSend", listUsers)
                    putParcelableArray("listArticlesSend", listArticles)
                }
            })
        }

        imgArticleReader.setOnClickListener {

            (activity as MainActivity?)?.replaceFragment(DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArray("listUsersSend", listUsers)
                    putParcelableArray("listArticlesSend", listArticles)
                    putInt("indexArticleSend", indexArticle)
                }
            })
        }
    }

    fun append(arr: Array<Int>, element: Int): Array<Int> {
        val list: MutableList<Int> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }
}