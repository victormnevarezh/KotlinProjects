package com.example.practicafragmento

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class BlueFragment : Fragment(R.layout.fragment_blue) {
    override fun onResume() {
        super.onResume()

        Toast.makeText(context, requireArguments().getString("key", ""), Toast.LENGTH_SHORT).show()
    }
}