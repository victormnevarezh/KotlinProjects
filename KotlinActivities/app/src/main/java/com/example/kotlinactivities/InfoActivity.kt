package com.example.kotlinactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class InfoActivity : AppCompatActivity() {

    //Image Views
    private lateinit var imgInfoPrincipal: ImageView
    private lateinit var imgBackInfo: ImageView
    private lateinit var imgStarInfo: ImageView

    //Text Views
    private  lateinit var txtNameInfo: TextView
    private lateinit var txtPrincipalInfo: TextView

    //List
    private var listAnimals: MutableList<MarineAnimal> = mutableListOf()

    //Current Animal
    private var currentAnimal: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        initView()
        listAnimals = MarineAnimal.list()
        setImage()
        setText()
    }

    fun initView() {
        currentAnimal = intent.getIntExtra("currentAnimal", 1)

        imgInfoPrincipal = findViewById(R.id.imgPrincipalInfo)
        imgStarInfo = findViewById(R.id.imgStar)

        txtNameInfo = findViewById(R.id.txtNameInfoActivity)
        txtPrincipalInfo = findViewById(R.id.txtPrincipalInfoActivity)
    }

    fun setImage(){
        imgInfoPrincipal.setImageResource(listAnimals.elementAt(currentAnimal).image)
    }

    fun setText(){
        txtNameInfo.text = "${listAnimals.elementAt(currentAnimal).name}"
        txtPrincipalInfo.text = "${listAnimals.elementAt(currentAnimal).info}"
    }

    fun onClickImgInfo(view: android.view.View) {
        startActivity(Intent(this, ImageActivity::class.java).apply {
            putExtra("currentAnimal", currentAnimal)
        })
    }
}