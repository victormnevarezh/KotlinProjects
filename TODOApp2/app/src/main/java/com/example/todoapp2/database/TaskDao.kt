package com.example.todoapp2.database

import androidx.room.*
import com.example.todoapp2.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task WHERE status = 1")
    suspend fun getPendingTasks(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task)
}