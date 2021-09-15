package com.example.examen1erperiodo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged

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
        imgLogIn.setImageResource(R.drawable.avatar)
        editTextUser = view.findViewById(R.id.editTextUser)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        btnLogIn = view.findViewById(R.id.btnLogIn)



        // TODO: 15/09/2021 setEvents
    }

    private fun changedEditTextUser () {
        editTextUser.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //No usado
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                listUsers.forEach {
                    if (p0 == it.username) {
                        imgLogIn.setImageResource(it.image)
                    }
                    else {
                        imgLogIn.setImageResource(R.drawable.avatar)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                //No usado
            }

        })
    }

}