package com.example.appmaestro.core.platform

import android.content.Context
import android.content.SharedPreferences
import com.example.appmaestro.domain.model.Maestro
import com.example.appmaestro.domain.model.Materia
//import com.proberto666.examenandroid2.domain.model.User
import com.squareup.moshi.Moshi
import javax.inject.Inject

class AuthManager @Inject constructor(private val context: Context) {
    private val PREFS_KEY = "ESCUELA_PREFS"
    private val MATERIA_KEY = "MATERIA"
    private val MAESTRO_KEY = "MAESTRO"

    private var preferences : SharedPreferences =
        context.getSharedPreferences(PREFS_KEY,Context.MODE_PRIVATE)
    private val moshi = Moshi.Builder().build()


    var materia:Materia?
        set(value)= preferences.edit()
            .putString(MATERIA_KEY, moshi.adapter(Materia::class.java).toJson(value)).apply()
        get() = preferences.getString(MATERIA_KEY,null)?.let {
            return@let try{
                moshi.adapter(Materia::class.java).fromJson(it)
            }catch (e: Exception){
                null
            }
        }

    var maestro: Maestro?
        set(value)= preferences.edit()
            .putString(MAESTRO_KEY, moshi.adapter(Maestro::class.java).toJson(value)).apply()
        get() = preferences.getString(MAESTRO_KEY,null)?.let {
            return@let try{
                moshi.adapter(Maestro::class.java).fromJson(it)
            }catch (e: Exception){
                null
            }
        }

}