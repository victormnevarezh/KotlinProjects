package com.example.examen1erperiodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ReaderFragment : Fragment(R.layout.fragment_reader) {

    //Array
    private lateinit var listUsers: Array<User>
    private lateinit var listArticles: Array<Article>

    override fun onCreate(savedInstanceState: Bundle?) {

        //Recibe los argumentos desde el Main Activity
        listUsers = requireArguments().getParcelableArray("listUsersSend") as Array<User>
        listArticles = requireArguments().getParcelableArray("listArticlesSend") as Array<Article>

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}