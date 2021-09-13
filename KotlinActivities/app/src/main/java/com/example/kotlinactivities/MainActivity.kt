package com.example.kotlinactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //Image Views
    private lateinit var imgPrincipal: ImageView
    private lateinit var arrowRight: ImageView
    private lateinit var arrowLeft: ImageView

    //Buttons
    private lateinit var btnInfo: Button

    //Text Views
    private lateinit var txtName: TextView

    //List
    private var listAnimals: MutableList<MarineAnimal> = mutableListOf()

    //Current Animal
    private var currentAnimal: Int = 0

    //OnCreate Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    //Initialization Activity Main
    fun init() {
        initViews()
        listAnimals = MarineAnimal.list()
        actionChange()
        setImagesArrows()
        actionChange()
    }

    //This function is used everytime both the image and the text are needed to change
    fun actionChange() {
        setImage()
        setText()
    }

    //Assigning values of the widgets to the variables in MainActivity.kt
    fun initViews() {
        imgPrincipal = findViewById(R.id.imgPrincipal)
        arrowLeft = findViewById(R.id.imgLeft)
        arrowRight = findViewById(R.id.imgRight)
        btnInfo = findViewById(R.id.btnInfo)
        txtName = findViewById(R.id.txtName)
    }

    //This functions displays the image in imgPrincipal
    fun setImage(){
        imgPrincipal.setImageResource( listAnimals.elementAt(currentAnimal).image)
    }

    //This functions displays the corresponding images for both left and right arrow
    fun setImagesArrows(){
        arrowLeft.setImageResource(R.drawable.arrowleft)
        arrowRight.setImageResource(R.drawable.arrowright)
    }

    //This functions displays the name in txtName
    fun setText()  {
        txtName.text = "${listAnimals.elementAt(currentAnimal).name}"
    }

    fun onClickLeft(view: android.view.View) {
        if (!listAnimals.isEmpty()) {
            if ((currentAnimal+1) < listAnimals.size) {
                currentAnimal++

            }
            else if ((currentAnimal+1) >= listAnimals.size ) {
                currentAnimal = 0
            }
            actionChange()
        }
    }

    fun onClickRight(view: android.view.View) {
        if (!listAnimals.isEmpty()) {
            if (currentAnimal > 0) {
                currentAnimal--
            } else if (currentAnimal <= 0) {
                currentAnimal = listAnimals.lastIndex
            }
            actionChange()
        }
    }

    fun onClickBtnInfo(view: android.view.View) {
        startActivity(Intent(this, InfoActivity::class.java).apply {
            putExtra("currentAnimal", currentAnimal)
        })
    }
}