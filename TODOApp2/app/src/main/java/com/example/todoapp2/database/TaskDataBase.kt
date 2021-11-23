package com.example.todoapp2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapp2.Task

@Database(entities = [Task::class], version = 1)
@TypeConverters(TaskConverter::class)
abstract class TaskDataBase() : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}