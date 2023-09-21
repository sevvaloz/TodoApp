package com.ozdamarsevval.todoapp.utils

interface TodoItemClickListener<T> {
    fun onItemDeleteClick(item: T)
}