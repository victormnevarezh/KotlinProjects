package com.example.examen1erperiodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class LogInFragment : Fragment(R.layout.fragment_log_in) {

    //Array
    private lateinit var listUsers: Array<User>

    //Image View
    private lateinit var imgLogIn: ImageView

    //Edit Text
    private lateinit var editTextUser: EditText
    private lateinit var editTextPassword: EditText

    //Button
    private lateinit var btnLogIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        listUsers = requireArguments().getParcelableArray("listSended") as Array<User>

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity?)?.savePreferences(listUsers)

        imgLogIn = view.findViewById(R.id.imgLogIn)
        editTextUser = view.findViewById(R.id.editTextUser)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        btnLogIn = view.findViewById(R.id.btnLogIn)
        
    }
}