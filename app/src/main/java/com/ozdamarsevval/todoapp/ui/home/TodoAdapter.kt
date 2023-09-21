package com.ozdamarsevval.todoapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ozdamarsevval.todoapp.R
import com.ozdamarsevval.todoapp.model.Todo
import com.ozdamarsevval.todoapp.databinding.ItemTodoBinding
import com.ozdamarsevval.todoapp.utils.TodoItemClickListener

class TodoAdapter(private val clickListener: TodoItemClickListener<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val todoList = mutableListOf<Todo>()

    class TodoViewHolder(val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.binding.apply {
            title.text = todo.title
            description.text = todo.description
            checkBox.isChecked = todo.isChecked

            checkBox.setOnClickListener {
                clickListener.onItemDeleteClick(todo)
            }

            val backgroundColor = when (todo.priority) {
                1 -> ContextCompat.getColor(root.context, R.color.lowPriority)
                2 -> ContextCompat.getColor(root.context, R.color.mediumPriority)
                3 -> ContextCompat.getColor(root.context, R.color.highPriority)
                else -> ContextCompat.getColor(root.context, R.color.gray)
            }
            root.setCardBackgroundColor(backgroundColor)
        }
    }

    override fun getItemCount(): Int = todoList.size

    fun updateTodoList(newList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()
    }
}