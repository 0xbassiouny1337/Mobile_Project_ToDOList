package com.example.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String? = null,
    val dueAt: Long? = null,
    val priority: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false
)
