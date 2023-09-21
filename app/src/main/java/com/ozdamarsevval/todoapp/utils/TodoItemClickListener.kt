package com.ozdamarsevval.todoapp.utils

interface TodoItemClickListener<T> {
    fun onItemDeleteClick(position: Int, item: T)
}