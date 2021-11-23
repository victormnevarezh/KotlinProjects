package com.example.todoapp2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.todoapp2.database.TaskDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.Month
import java.time.OffsetDateTime
import java.util.*
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    companion object {
        val NEW_TASK = 200
        val NEW_TASK_KEY = "new_task"
        val UPDATE_TASK = 201
    }
    private val TASKS = "tasks"
    private var tasks = mutableListOf<Task>()

    private lateinit var add: FloatingActionButton
    private lateinit var rcv: RecyclerView
    private lateinit var adapter: TasksAdapter

    private lateinit var db: TaskDatabase

    //Mínimo necesario para una app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            val savedTasks = it.getParcelableArrayList<Task>(TASKS)?.toMutableList() ?: tasks
            tasks = savedTasks
        }

        initViews()
        setAdapter()
    }

    //Inicializar vistas
    private fun initViews() {
        add = findViewById(R.id.add)
        rcv = findViewById(R.id.rcvTasks)
        add.setOnClickListener {
            startActivityForResult(Intent(this, FormActivity::class.java), NEW_TASK)
        }
    }

    override fun onResume() {
        super.onResume()
        db = Room.databaseBuilder(this, TaskDatabase::class.java, "Tasks").build()

        MainScope().launch {
            tasks = db.taskDao().getPendingTasks().toMutableList()
            setAdapter()
        }
    }


    private fun setAdapter() {
        adapter = TasksAdapter(
            tasks,
            onClickFinishedTask = {
                    task, position ->
                MainScope().launch {
                    db.taskDao().updateTask(task.apply {
                        status = false
                    })
                    adapter.removeTask(position)
                    val workManager = WorkManager.getInstance(this@MainActivity)
                    workManager.cancelUniqueWork("NOTIFICATION_WORK ${task.id}")
                }
            },
            onClickDetailsTask = {
                    task ->
                startActivityForResult(Intent(this, FormActivity::class.java).apply {
                    putExtra("isTaskDetail", true)
                    putExtra("task", task)
                }, UPDATE_TASK)
            }
        )
        rcv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcv.adapter = adapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putParcelableArrayList(TASKS, tasks as ArrayList<Task>)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ( requestCode == NEW_TASK ) {
            data?.getParcelableExtra<Task>(NEW_TASK_KEY)?.let {
                MainScope().launch(Dispatchers.Main){
                    adapter.addTask(it)
                }

                MainScope().launch(Dispatchers.IO){
                    val id = db.taskDao().saveNewTask(it)

                    val zone = OffsetDateTime.now().offset
                    val selectedMillis = it.Date?.toInstant(zone)?.toEpochMilli() ?:0
                    val nowMillis = LocalDateTime.now().toInstant(zone).toEpochMilli()

                    scheduleNotification(selectedMillis - nowMillis, Data.Builder().apply {
                        putInt("notificationID", id.toInt())
                        putString("notificationTitle", it.Title)
                        putString("notificationDescription", it.Description)
                        putString("notificationDate", it.Date.toString())
                    }.build())
                }
            }
        } else {
            data?.getParcelableExtra<Task>(NEW_TASK_KEY)?.let {
                MainScope().launch(Dispatchers.Main) {
                    adapter.updateTask(it)
                }

                MainScope().launch(Dispatchers.IO) {
                    db.taskDao().updateTask(it)
                }
            }
        }
    }

    private fun scheduleNotification(delay: Long, data: Data){
        val notificationWork = OneTimeWorkRequest.Builder(com.example.todoapp2.notification.NotificationManager::class.java)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS).setInputData(data).build()
        val instanceWorkManager = WorkManager.getInstance(this)
        instanceWorkManager.beginUniqueWork(
            "NOTIFICATION_WORK ${data.getInt("notificationID", 0)}",
            ExistingWorkPolicy.APPEND_OR_REPLACE, notificationWork
        ).enqueue()
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "TASK"
            val descriptionText = "Channel of pending tasks"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("TASK_CHANNEL", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}