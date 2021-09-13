package com.example.kotlinactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

//Image Views
private lateinit var imgPrincipalImage: ImageView
private lateinit var imgBackImage: ImageView

//Text Views
private  lateinit var txtNameInfo: TextView
private lateinit var txtPrincipalInfo: TextView

//List
private var listAnimals: MutableList<MarineAnimal> = mutableListOf()

//Current Animal
private var currentAnimal: Int = 0

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        initView()
        listAnimals = MarineAnimal.list()
        setImage()
    }

    fun initView() {
        currentAnimal = intent.getIntExtra("currentAnimal", 1)

        imgPrincipalImage = findViewById(R.id.imgPrincipalImage)
    }

    fun setImage(){
        imgPrincipalImage.setImageResource(listAnimals.elementAt(currentAnimal).image)
    }
}