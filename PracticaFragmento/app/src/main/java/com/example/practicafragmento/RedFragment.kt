package com.example.practicafragmento

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class RedFragment : Fragment(R.layout.fragment_red) {

    private val TEXT_KEY = "TEXT_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(
            context,
            savedInstanceState?.getString(TEXT_KEY, "") ?: "",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(TEXT_KEY, "La pantalla ha rotado")
        }

        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(context, requireArguments().getString("key", ""), Toast.LENGTH_SHORT).show()
    }
}