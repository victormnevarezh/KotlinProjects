package com.example.todoapp2

import java.time.LocalDateTime

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val dateTime: LocalDateTime
)
