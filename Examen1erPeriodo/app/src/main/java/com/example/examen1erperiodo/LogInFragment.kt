package com.example.examen1erperiodo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged

class LogInFragment : Fragment(R.layout.fragment_log_in) {

    //Array
    private lateinit var listUsers: Array<User>
    private lateinit var listArticles: Array<Article>

    //Image View
    private lateinit var imgLogIn: ImageView

    //Edit Text
    private lateinit var editTextUser: EditText
    private lateinit var editTextPassword: EditText

    //Button
    private lateinit var btnLogIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        //Recibe los argumentos desde el Main Activity
        listUsers = requireArguments().getParcelableArray("listUsersSend") as Array<User>
        listArticles = requireArguments().getParcelableArray("listArticlesSend") as Array<Article>

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity?)?.savePreferences(listUsers)

        imgLogIn = view.findViewById(R.id.imgLogIn)
        imgLogIn.setImageResource(R.drawable.avatar)
        editTextUser = view.findViewById(R.id.editTextUser)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        editTextPassword.isEnabled = false
        btnLogIn = view.findViewById(R.id.btnLogIn)
        btnLogIn.isEnabled = false

        setEvents()
    }

    private fun setEvents() {
        editTextUser.doAfterTextChanged {
            val stringUser: String = editTextUser.text.toString()
            println("stringUser: $stringUser")
            var imgCambio = false
            listUsers.forEach {
                if (!imgCambio) {
                    imgLogIn.setImageResource(R.drawable.avatar)
                    editTextPassword.isEnabled = false
                    btnLogIn.isEnabled = false
                    if(it.username == stringUser) {
                        imgCambio = true
                        imgLogIn.setImageResource(it.image)
                        editTextPassword.isEnabled = true
                    }
                }
            }
        }

        editTextPassword.doAfterTextChanged {
            if (editTextPassword.toString() == "") {
                btnLogIn.isEnabled = false
            } else if (editTextPassword.toString() != "") {
                btnLogIn.isEnabled = true
            }
        }

        // TODO: btnLogIn
        btnLogIn.setOnClickListener {
            listUsers.forEach {
                if ((it.username).equals(editTextUser.text) && (it.password).equals(editTextPassword.text)) {
                    when (it.loged) {
                        true -> {
                            (activity as MainActivity?)?.replaceFragment(WriterFragment().apply {
                                arguments = Bundle().apply {
                                    putParcelableArray("listUsersSend", listUsers)
                                    putParcelableArray("listArticlesSend", listArticles)
                                }
                            })
                        }
                        false -> {
                            (activity as MainActivity?)?.replaceFragment(ReaderFragment().apply {
                                arguments = Bundle().apply {
                                    putParcelableArray("listUsersSend", listUsers)
                                    putParcelableArray("listArticlesSend", listArticles)
                                }
                            })
                        }
                    }
                }
                else {
                    Toast.makeText(context,"Datos de ingreso incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}