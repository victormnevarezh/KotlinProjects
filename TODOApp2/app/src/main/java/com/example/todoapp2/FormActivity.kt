package com.example.todoapp2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.todoapp2.MainActivity.Companion.NEW_TASK
import com.example.todoapp2.MainActivity.Companion.NEW_TASK_KEY
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month
import java.time.format.DateTimeFormatter

class FormActivity : AppCompatActivity() {

    private lateinit var edtTitle: EditText
    private lateinit var edtDescription: EditText
    private lateinit var edtDate: EditText
    private lateinit var edtTime: EditText
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        initViews()
    }

    private fun initViews() {
        edtTitle = findViewById(R.id.edtTitle)
        edtDescription = findViewById(R.id.edtDescription)
        edtDate = findViewById(R.id.edtDate)
        edtTime = findViewById(R.id.edtTime)
        btnAdd = findViewById(R.id.btnAdd)

        edtDate.setOnClickListener {
            val nowDate = LocalDate.now()

            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    edtDate.setText(String.format("%02d/%02d/%04d",dayOfMonth,month+1,year))
                },
                nowDate.year,
                nowDate.monthValue - 1,
                nowDate.dayOfMonth
            ).show()
        }

        edtTime.setOnClickListener {
            val nowTime = LocalTime.now()

            TimePickerDialog(
                this,
                { _, hour, minute ->
                    edtTime.setText(String.format("%02d:%02d", hour,minute))
                },
                nowTime.hour,
                nowTime.minute,
                true
            ).show()
        }

        btnAdd.setOnClickListener {
            if (edtTitle.text.toString().equals("") && edtDescription.text.toString().equals("") && edtDate.text.toString().equals("") && edtTime.text.toString().equals(""))
            {
                setResult(
                    NEW_TASK,
                    Intent().putExtra(
                        NEW_TASK_KEY,
                        Task(
                            10,
                            edtTitle.text.toString(),
                            edtDescription.text.toString(),
                            LocalDateTime.of(
                                LocalDate.parse(
                                    edtDate.text,
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                ),
                                LocalTime.parse(
                                    edtTime.text,
                                    DateTimeFormatter.ofPattern("HH:mm")
                                )
                            )
                        )
                    )
                )
            } else {
                Toast.makeText(this, "Llena todos los campos por favor", Toast.LENGTH_SHORT).show()
            }

        }
    }
}