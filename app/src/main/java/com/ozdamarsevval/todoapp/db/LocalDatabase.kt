package com.ozdamarsevval.todoapp.db

import com.ozdamarsevval.todoapp.model.Todo

object LocalDatabase {

    private val todoList = mutableListOf<Todo>()
    fun getTodos(): List<Todo> = todoList.reversed()

    fun addTodo(
        title: String,
        description: String,
        priority: Int
    ) {
        val newTodo = Todo(
            id= (todoList.lastOrNull()?.id ?: 0) + 1,
            title = title,
            description = description,
            priority = priority
        )
        todoList.add(newTodo)
    }

    fun deleteTodo(id: Int) {
        val removedTodo = todoList.firstOrNull { it.id == id }
        removedTodo?.let {
            todoList.remove(it)
        }
    }

}