package com.example.todoapp2

import android.os.Build
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class TasksAdapter(

    private val list: MutableList<Task>,
    var onClickFinishedTask: (task: Task, position: Int) -> Unit,
    var onClickDetailsTask: (task: Task) -> Unit

) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    fun addTask(task: Task) {
        list.add(task)
        notifyItemInserted(list.size - 1)
    }

    fun updateTask(task: Task){
        val index = list.indexOfFirst { it.id == task.id }
        list[index] = task
        notifyItemChanged(index)
    }

    fun removeTask(position: Int){
        list.removeAt(position)
        notifyItemRemoved(position)
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

            txvTitle.text = data.Title
            txvDateTime.text = data.Date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a"))

            chkFinished.setOnClickListener{
                onClickFinishedTask(data, adapterPosition)
            }

            rootView.setOnClickListener{
                onClickDetailsTask(data)
            }

        }
    }
}