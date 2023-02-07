package com.rahim.taskmanager.ui.task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rahim.taskmanager.databinding.FragmentTaskBinding
import com.rahim.taskmanager.App
import com.rahim.taskmanager.App.Companion.db
import com.rahim.taskmanager.model.TaskModel

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private val db = Firebase.firestore

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
            save()
        }

    }

    private fun save() {
        val task = TaskModel(
            title = binding.edTitle.text.toString(),
            desc = binding.edDesc.text.toString()
        )
        putTask(task)
        App.db.taskDao().insert(task)
        findNavController().navigateUp()

    }



    private fun putTask(taskModel: TaskModel){
        FirebaseAuth.getInstance().currentUser?.uid?.let { db.collection(it).
        add(taskModel).addOnSuccessListener {
            Log.e("ololo", "save: success" )
        }.addOnFailureListener{
            Log.e("ololo", "save: " + it.message )
        }
    }
}

    companion object {
        const val RESULT_TASK = "result"
    }
}
