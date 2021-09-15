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
        editTextPassword.isEnabled = false
        btnLogIn = view.findViewById(R.id.btnLogIn)
        btnLogIn.isEnabled = false

        setEvents()

        // TODO: 15/09/2021 setEvents
    }

    private fun setEvents() {
        editTextUser.doAfterTextChanged {
            val stringUser: String = editTextUser.text.toString()
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
            var acceptedCombination = false
            var user = editTextUser.toString()
            var password = editTextPassword.toString()

            listUsers.forEach {
                println("Entro al foreach")
                if(!acceptedCombination) {
                    println("acceptedCombination era false")
                    if(user == it.username && password == it.password) {
                        println("Ambas datos eran correctos")
                        it.loged = true
                        //If de tipo de usuario
                        if (it.type) {
                            println("Entro a iniciar como escritor")
                            (activity as MainActivity?)?.replaceFragment(WriterFragment().apply {
                                arguments = Bundle().apply {
                                    putParcelableArray("listUsers", listUsers)
                                }
                            })
                        }
                        else {
                            println("Entro a iniciar como lector")
                            (activity as MainActivity?)?.replaceFragment(ReaderFragment().apply {
                                arguments = Bundle().apply {
                                    putParcelableArray("listUsers", listUsers)
                                }
                            })
                        }
                    }
                }
            }
        }
    }


}