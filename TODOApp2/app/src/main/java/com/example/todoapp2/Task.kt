package com.example.todoapp2

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
@Entity
@RequiresApi(Build.VERSION_CODES.O)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val Title: String = "",
    val Description: String = "",
    val Date: LocalDateTime = LocalDateTime.now(),
    var status: Boolean = true
): Parcelable
