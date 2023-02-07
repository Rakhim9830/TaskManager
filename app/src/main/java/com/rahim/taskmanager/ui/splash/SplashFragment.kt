package com.rahim.taskmanager.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rahim.taskmanager.App
import com.rahim.taskmanager.App.Companion.db
import com.rahim.taskmanager.R
import com.rahim.taskmanager.data.Pref
import com.rahim.taskmanager.databinding.FragmentAcceptBinding
import com.rahim.taskmanager.databinding.FragmentSplashBinding
import com.rahim.taskmanager.model.TaskModel


class SplashFragment : Fragment() {
    private lateinit var binding:FragmentSplashBinding
    private lateinit var pref: Pref
    private  var auth = FirebaseAuth.getInstance()
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        auth = FirebaseAuth.getInstance()
        val tasks = App.db.taskDao().getAll()
        val uid = FirebaseAuth.getInstance().currentUser?.uid

        if (uid != null){
           db.collection(uid).get().addOnSuccessListener {
              val data = it.toObjects(TaskModel::class.java)
               val duplicates = tasks.minus(data.toSet())
               if (duplicates.isNotEmpty()){
                   db.collection(uid).add(duplicates).addOnSuccessListener {
                       findNavController().navigate(R.id.navigation_home)
                   }.addOnFailureListener{
                       startFragment()
                   }
               }
           }.addOnFailureListener{
                 startFragment()
           }
        }

       else{startFragment()

       }
    }

    private fun startFragment(){
        Handler(Looper.getMainLooper()).postDelayed({},1200)
        if (!pref.isUserSeen()){
            findNavController().navigate(R.id.boarding)
        }
        else if (auth.currentUser == null){
            findNavController().navigate(R.id.authFragment)
        }
        else{
            findNavController().navigate(R.id.navigation_home)
        }
    }
    }
