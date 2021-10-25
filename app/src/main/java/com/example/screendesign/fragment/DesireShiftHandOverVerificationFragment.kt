package com.example.screendesign.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.screendesign.R
import com.example.screendesign.activity.DesireShiftHandOverActivity
import com.example.screendesign.databinding.DesireShiftHandOverVerificationFragmentBinding
import com.example.screendesign.viewmodel.DesireShiftHandOverVerificationViewModel
import com.example.screendesign.activity.MainActivity




class DesireShiftHandOverVerificationFragment : Fragment() {

    companion object {
        fun newInstance() = DesireShiftHandOverVerificationFragment()
    }

    private lateinit var binding: DesireShiftHandOverVerificationFragmentBinding
    private lateinit var viewModel: DesireShiftHandOverVerificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.desire_shift_hand_over_verification_fragment, container, false)

        binding.fixBtn.setOnClickListener {
            val intent = Intent(requireContext(),DesireShiftHandOverActivity::class.java)
            startActivity(intent)
        }

        binding.verificationBtn2.setOnClickListener {
            val intent = Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(DesireShiftHandOverVerificationViewModel::class.java)
    }

}