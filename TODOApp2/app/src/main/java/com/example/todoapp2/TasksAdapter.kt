package com.example.todoapp2

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(private val list: MutableList<Task>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = list.size

    inner class TaskViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Task) {

        }
    }
}