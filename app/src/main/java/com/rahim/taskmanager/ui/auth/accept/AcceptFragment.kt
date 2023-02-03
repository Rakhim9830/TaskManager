package com.rahim.taskmanager.ui.auth.accept

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.rahim.taskmanager.R
import com.rahim.taskmanager.databinding.FragmentAcceptBinding


class AcceptFragment : Fragment() {
    private lateinit var binding: FragmentAcceptBinding
    private lateinit var args: AcceptFragmentArgs
    private lateinit var auth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcceptBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args = arguments?.let { AcceptFragmentArgs.fromBundle(it) }!!
        auth = FirebaseAuth.getInstance()
        binding.btnAccept.setOnClickListener {
            val credential = PhoneAuthProvider.getCredential(args.verId, binding.edCode.text.toString())
            signInWithPhoneAuthCredential(credential)
        }
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(AcceptFragmentDirections.actionAcceptFragmentToNavigationHome())
                    val user = task.result?.user
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    }
                }
            }
    }


}