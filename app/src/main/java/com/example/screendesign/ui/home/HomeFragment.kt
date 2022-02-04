package com.example.screendesign.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.screendesign.activity.ConfirmationAlreadyShiftHandOverActivity
import com.example.screendesign.activity.DesireShiftHandOverActivity
import com.example.screendesign.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this,HomeViewModelFactory(requireContext()))[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.confirmationAlreadyShiftHandOverTransitionBtn.setOnClickListener {
            startActivity(Intent(requireContext(),ConfirmationAlreadyShiftHandOverActivity::class.java))
        }

        binding.desireShiftHandOverTransitionBtn.setOnClickListener {
            startActivity(Intent(requireContext(),DesireShiftHandOverActivity::class.java))
        }

//        binding.notiCheck?.setOnClickListener {
//            homeViewModel.checkNotification()
//        }
//
//        binding.notiStart.setOnClickListener {
//            homeViewModel.notificationStart()
//        }
//
//        binding.notiEnd.setOnClickListener {
//            homeViewModel.notificationEnd()
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

