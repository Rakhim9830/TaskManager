package com.rahim.taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rahim.taskmanager.R
import com.rahim.taskmanager.databinding.FragmentHomeBinding
import com.rahim.taskmanager.App
import com.rahim.taskmanager.isOnline
import com.rahim.taskmanager.model.TaskModel
import com.rahim.taskmanager.ui.task.adapter.Adapter

class HomeFragment : Fragment() , Adapter.Listener{
    private lateinit var builder: AlertDialog.Builder
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: Adapter
  private  var db = Firebase.firestore
    private val binding get() = _binding!!
    private val task: TaskModel
        get() {
            TODO()
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = Adapter(this)
        builder = AlertDialog.Builder(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       if (requireContext().isOnline()){
             getTask()
        }else{
           setData()
        }
        binding.recycleTask.adapter = adapter
        binding.btnPlus.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToTaskFragment())
        }
        setDataFromFirebase()
    }
    private fun getTask(){
  val uid =  FirebaseAuth.getInstance().currentUser?.uid
      if (uid !=  null) {
          db.collection(uid).get().addOnSuccessListener {
              val data = it.toObjects(TaskModel::class.java)
              adapter.addTasks(data)
          }.addOnFailureListener {

          }
       }
    }
    override fun onClick(adapter: TaskModel) {
        builder.setTitle("Delete?").setMessage("Are you Sure?").setCancelable(true)
            .setPositiveButton("Yes") { _, _ ->
                App.db.taskDao().delete(adapter)
                setData()
            }.setNegativeButton("No") { DialogInterface, _ -> DialogInterface.cancel() }
        builder.create().show()
        super.onClick(adapter)
    }
    private fun setData() {
        val tasks = App.db.taskDao().getAll()
        adapter.addTasks(tasks)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setDataFromFirebase(){
        if (requireContext().isOnline()){
            getTask()
        }else{
            setData()
        }

    }

}
