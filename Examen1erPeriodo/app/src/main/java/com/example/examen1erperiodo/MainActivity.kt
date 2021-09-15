package com.example.examen1erperiodo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.squareup.moshi.Moshi
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    //List
    private lateinit var listUsers: Array<User>

    //SharedPreferences
    private val KEY = "MY_PREFERENCES"
    private val savingPreferences = "USER_PREFERENCES"
    private lateinit var preferences: SharedPreferences
    private val moshi = Moshi.Builder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences(KEY, Context.MODE_PRIVATE)
        listUsers = getSavedPreferences()

        supportFragmentManager.beginTransaction().add(R.id.container, LogInFragment().apply {
            arguments = Bundle().apply { putParcelableArray("listSended", listUsers)}
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

    fun savePreferences (User: Array<User>?=null) {
        preferences.edit().putString(savingPreferences,moshi.adapter(Array::class.java).toJson(listUsers)).apply()
    }

    fun getSavedPreferences() =
        preferences.getString(savingPreferences, null)?.let{
            return@let try {
                moshi.adapter(User.users::class.java).fromJson(it)
            }catch (e: Exception){
                User.users
            }
        }?:User.users
}