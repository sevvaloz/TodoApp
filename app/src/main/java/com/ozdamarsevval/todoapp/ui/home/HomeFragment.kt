package com.ozdamarsevval.todoapp.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.ozdamarsevval.todoapp.R
import com.ozdamarsevval.todoapp.databinding.DialogAddTodoBinding
import com.ozdamarsevval.todoapp.databinding.FragmentHomeBinding
import com.ozdamarsevval.todoapp.db.LocalDatabase
import com.ozdamarsevval.todoapp.model.Todo
import com.ozdamarsevval.todoapp.utils.TodoItemClickListener
import viewBinding

class HomeFragment : Fragment(R.layout.fragment_home), TodoItemClickListener<Todo> {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val todoAdapter = TodoAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvTodos.adapter = todoAdapter
            todoAdapter.updateTodoList(LocalDatabase.getTodos())
            floatingActionButton.setOnClickListener {
                showDialog()
            }
        }
    }

    private fun showDialog() {
        val bindingDialog = DialogAddTodoBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(bindingDialog.root)
        val dialog = builder.create()
        var priority = 0
        bindingDialog.apply {
            btnAddTodo.setOnClickListener {
                val title = tvTitle.text.toString()
                val description = tvDescription.text.toString()
                when (rgPriority.checkedRadioButtonId) {
                    rbLowPriority.id -> {
                        priority = 0
                    }
                    rbMediumPriority.id -> {
                        priority = 1
                    }
                    rbHighPriority.id -> {
                        priority = 2
                    }
                }
                LocalDatabase.addTodo(
                    title = title,
                    description = description,
                    priority = priority
                )
                todoAdapter.updateTodoList(LocalDatabase.getTodos())
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    override fun onItemDeleteClick(position: Int, item: Todo) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            LocalDatabase.deleteTodo(item.id)
            todoAdapter.updateTodoList(LocalDatabase.getTodos())
        }, 750)
    }
}