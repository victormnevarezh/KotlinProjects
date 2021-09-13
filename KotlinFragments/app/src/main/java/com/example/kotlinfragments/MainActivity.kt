package com.example.kotlinfragments

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import java.lang.Exception
import java.util.prefs.AbstractPreferences

class MainActivity : AppCompatActivity() {

    //Lista
    private lateinit var listaCast: Array<Personaje>

    //Inicializar MainFragment
    private val KEY = "MY_PREFERENCES"
    private val savingPreferences = "PERSONAJE_PREFERENCES"
    private lateinit var preferences: SharedPreferences
    private val moshi = Moshi.Builder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences(KEY, Context.MODE_PRIVATE)

        listaCast = getSavedPreferences()

        supportFragmentManager.beginTransaction().add(R.id.container, MainFragment().apply {
            arguments = Bundle().apply { putParcelableArray("listaSended", listaCast)}
        }).commit()
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {

            setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right,
                R.anim.slide_in_right,
                R.anim.slide_out_left)

            replace(R.id.container, fragment)
            addToBackStack(fragment.tag)
            commit()
        }
    }

    fun savePreferences (Personaje: Array<Personaje>?=null) {
        preferences.edit().putString(savingPreferences,moshi.adapter(Array::class.java).toJson(listaCast)).apply()
    }

    fun getSavedPreferences() =
        preferences.getString(savingPreferences, null)?.let{
            return@let try {
                moshi.adapter(Personaje.cast::class.java).fromJson(it)
            }catch (e: Exception){
                Personaje.cast
            }
        }?:Personaje.cast
}