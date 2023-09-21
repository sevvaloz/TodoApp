package com.ozdamarsevval.todoapp.model

data class Todo(
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Int,
    var isChecked: Boolean = false
)
