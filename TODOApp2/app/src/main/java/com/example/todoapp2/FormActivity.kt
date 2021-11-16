package com.example.todoapp2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
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
                    edtDate.setText("$dayOfMonth/$month/$year")
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
                    edtTime.setText("$hour:$minute")
                },
                nowTime.hour,
                nowTime.minute,
                true
            ).show()
        }

        btnAdd.setOnClickListener {
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
                            LocalTime.parse(edtTime.text, DateTimeFormatter.ofPattern("HH:mm"))
                        )
                    )
                )
            )
        }
    }
}