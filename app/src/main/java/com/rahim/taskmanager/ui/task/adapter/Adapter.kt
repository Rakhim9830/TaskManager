package com.rahim.taskmanager.ui.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahim.taskmanager.databinding.TaskmodelBinding
import com.rahim.taskmanager.model.TaskModel

class Adapter (val listener: Listener, private val updateClick:(TaskModel)->Unit) : RecyclerView.Adapter<Adapter.TaskViewHolder>() {

    private val data = arrayListOf<TaskModel>()

    fun addTask(tasks: TaskModel) {
        data.add(0, tasks)
        notifyItemChanged(0)
    }

    fun addTasks(list: List<TaskModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskmodelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class TaskViewHolder(private val binding: TaskmodelBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskModel, listener: Listener) {
            binding.descText.text = task.desc
            binding.taskText.text = task.title
            itemView.setOnLongClickListener() {
                listener.onClick(task)
                return@setOnLongClickListener true
            }
            itemView.setOnClickListener{
                updateClick(task)
            }

        }

    }

    interface Listener {
        fun onClick(adapter: TaskModel) {

        }
    }



}
