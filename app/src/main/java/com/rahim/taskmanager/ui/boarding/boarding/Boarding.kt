package com.rahim.taskmanager.ui.boarding.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.rahim.taskmanager.data.Pref
import com.rahim.taskmanager.databinding.FragmentBoardingBinding
import com.rahim.taskmanager.ui.boarding.boarding.adapter.Adapter

class Boarding: Fragment() {
    private lateinit var binding: FragmentBoardingBinding
    private lateinit var pref: Pref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoardingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        val adapter1 = Adapter() {
            pref.userSeen()
            findNavController().navigateUp()

        }

        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.pager

        viewPager.adapter = adapter1
        dotsIndicator.attachTo(viewPager)




        viewPager.adapter = adapter1

        binding.pager.adapter = adapter1


    }
}