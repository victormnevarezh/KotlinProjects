package com.example.appalumno.core.platform

import android.content.Context
import android.content.SharedPreferences
import com.example.appalumno.domain.model.Alumno
//import com.proberto666.examenandroid2.domain.model.User
import com.squareup.moshi.Moshi
import javax.inject.Inject

class AuthManager @Inject constructor(private val context: Context) {
    private val PREFS_KEY = "ALUMNI_PREFS"
    private val USER_KEY = "ALUMNI"

    private var preferences : SharedPreferences = context.getSharedPreferences(PREFS_KEY,Context.MODE_PRIVATE)
    private val moshi = Moshi.Builder().build()

    var alumno:Alumno?

    set(value)= preferences.edit().putString(USER_KEY, moshi.adapter(Alumno::class.java).toJson(value)).apply()

    get() = preferences.getString(USER_KEY,null)?.let {
        return@let try{
            moshi.adapter(Alumno::class.java).fromJson(it)
        }catch (e: Exception){
            null
        }
    }


}