package com.example.todoapp2

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import java.time.format.DateTimeFormatter

class TasksAdapter(private val list: MutableList<Task>) :
    RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    fun add(task: Task) {
        list.add(task)

        notifyItemInserted(list.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksAdapter.TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TasksAdapter.TaskViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount() = list.size

    inner class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Task, position: Int) = view.apply {

            val txvTitle = findViewById<TextView>(R.id.txvTitle)
            val txvDateTime = findViewById<TextView>(R.id.txvDateTime)
            val chkFinished = findViewById<MaterialCheckBox>(R.id.chkFinished)

            txvTitle.text = data.title
            txvDateTime.text = data.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm a"))

            chkFinished.setOnClickListener{
                list.removeAt(position)

                notifyItemRemoved(position)
            }

            rootView.setOnClickListener{ }

        }
    }
}