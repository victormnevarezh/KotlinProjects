package com.example.todoapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime
import java.time.Month
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        val NEW_TASK = 200
        val NEW_TASK_KEY = "newTask"
    }

    private lateinit var rcvTask: RecyclerView
    private lateinit var btnAddTask: FloatingActionButton

    //Key de estado
    private val SAVED_TASKS = "tasks"

    private lateinit var adapter: TasksAdapter
    private var tasks = mutableListOf(
        Task(0, "Test", "description", LocalDateTime.now()),
        Task(1, "Test1", "description1", LocalDateTime.of(2021, Month.NOVEMBER, 6, 12, 40)),
        Task(2, "Test2", "description2", LocalDateTime.of(2022, Month.NOVEMBER, 6, 12, 40)),
        Task(3, "Test3", "description3", LocalDateTime.of(2023, Month.NOVEMBER, 6, 12, 40)),
    )

    //Mínimo necesario para una app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            val savedTasks = it.getParcelableArrayList<Task>(SAVED_TASKS)?.toMutableList() ?: tasks
            tasks = savedTasks
        }

        initViews()
        setAdapter()
    }

    //Inicializar vistas
    private fun initViews() {
        rcvTask = findViewById(R.id.rcvTasks)
        btnAddTask = findViewById(R.id.btnAddTask)

        btnAddTask.setOnClickListener{
            startActivityForResult(Intent(this, FormActivity::class.java), NEW_TASK)
        }
    }

    private fun setAdapter() {
        adapter = TasksAdapter(tasks)

        rcvTask.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcvTask.adapter = adapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.apply {
            putParcelableArrayList(SAVED_TASKS, tasks as ArrayList<Task>)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_TASK) {
            data?.getParcelableExtra<Task>(NEW_TASK_KEY)?.let {
                adapter.add(it)
            }
        }
    }
}