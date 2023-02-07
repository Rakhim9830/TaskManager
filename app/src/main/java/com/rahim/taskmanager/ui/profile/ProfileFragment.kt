package com.rahim.taskmanager.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rahim.taskmanager.R
import com.rahim.taskmanager.data.Pref
import com.rahim.taskmanager.databinding.FragmentProfileBinding
import com.rahim.taskmanager.loadIMage


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref
    private var firebase = Firebase.firestore


    private val launcher = registerForActivityResult<Intent,ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK
            && result.data != null
        ) {
            val photoUri = result.data?.data
            pref.saveImage(photoUri.toString())
            binding.profileImage.loadIMage(photoUri.toString())

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.profileEd.setText(pref.getName())
        binding.ageId.setText(pref.getAge())
        binding.profileImage.loadIMage(pref.getImage())

        binding.btnProfile.setOnClickListener {
            pref.saveName(binding.profileEd.text.toString())
            pref.saveAge(binding.ageId.text.toString())


        }
        binding.profileImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }

        binding.btnExit.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
        }

    }
}