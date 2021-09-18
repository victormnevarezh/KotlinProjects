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
        btnArticleEdit = view.findViewById(R.id.btnEditDetail)
        btnArticleDelete = view.findViewById(R.id.btnArticleDelete)
    }

}