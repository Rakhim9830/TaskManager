package com.rahim.taskmanager.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rahim.taskmanager.databinding.FragmentTaskBinding
import com.rahim.taskmanager.App
import com.rahim.taskmanager.model.TaskModel

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            App.db.taskDao().insert(
                TaskModel(
                    title = binding.edTitle.text.toString(),
                    desc = binding.edDesc.text.toString()
                )
            )
            findNavController().navigateUp()
        }

    }


    companion object {
        const val RESULT_TASK = "result"
    }
}
