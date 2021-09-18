package com.example.examen1erperiodo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.squareup.moshi.Moshi
import java.lang.Exception
import com.example.examen1erperiodo.User.Companion as UserV

class MainActivity : AppCompatActivity() {

    //List
    private lateinit var listUsers: Array<User>
    private lateinit var listArticles: Array<Article>

    //SharedPreferences
    private lateinit var preferencesLogin: SharedPreferences //Archivo
    private val KEY_PREFERENCES = "USER_PREFERENCES" //Key del archivo
    private val KEY_USER = "LIST_USER_PREFERENCES" //Key de user
    private val KEY_ARTICLE = "LIST_ARTICLE_PREFERENCES" //Key de article

    //Moshi
    private val moshi = Moshi.Builder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recibe las preferences de la aplicación
        preferencesLogin = getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)

        //Lee las preferencias del documento y las asigna a las listas
        listUsers = getSavedPreferencesUser()
        listArticles = getSavedPreferencesArticle()

        //todo sustituir comprobacion de si hay un usuario loged y en caso de que haya iniciar sesión

        supportFragmentManager.beginTransaction().add(R.id.container, LogInFragment().apply {
            arguments = Bundle().apply {
                putParcelableArray("listUsersSend", listUsers)
                putParcelableArray("listArticlesSend", listArticles)
            }
        }).commit()
    }

    //SharedPreferences User
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

    fun savePreferences(listUsers: Array<User>, listArticles: Array<Article>) {
        preferencesLogin.edit().putString(KEY_USER,moshi.adapter(Array::class.java).toJson(this.listUsers)).apply()
        preferencesLogin.edit().putString(KEY_ARTICLE,moshi.adapter(Array::class.java).toJson(listArticles)).apply()
    }

    fun getSavedPreferencesUser () =
        preferencesLogin.getString(KEY_USER, null)?.let{
            return@let try {
                moshi.adapter(UserV.users::class.java).fromJson(it)
            }catch (e: Exception){
                UserV.users
            }
        }?:UserV.users

    fun getSavedPreferencesArticle () =
        preferencesLogin.getString(KEY_ARTICLE, null)?.let{
            return@let try {
                moshi.adapter(Article.publishedArticles::class.java).fromJson(it)
            }catch (e: Exception){
                Article.publishedArticles
            }
        }?:Article.publishedArticles


}