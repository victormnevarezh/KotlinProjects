package com.example.todoapp2.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.todoapp2.FormActivity
import com.example.todoapp2.R
import com.example.todoapp2.Task
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class NotificationManager(private val context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val id = inputData.getInt("notificationID", 0)
        val title = inputData.getString("notificationTitle") ?: ""
        val description = inputData.getString("notificationDescription") ?: ""
        val date = LocalDateTime.parse(inputData.getString("notificationDate"))

        createNotification(Task(id, title, description, date))

        return Result.success()
    }

    fun createNotification(task: Task){
        val intent: PendingIntent = PendingIntent.getActivity(
            context,
            1,
            Intent(context, FormActivity::class.java).apply {
                putExtra("isTaskDetail", true)
                putExtra("task", task)
            },
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, "TASK_CHANNEL")
            .setSmallIcon(R.drawable.ic_add)
            .setContentTitle(task.Title)
            .setContentText(task.Description)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentIntent(intent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(context)){
            notify(task.id, builder.build())
        }
    }
}