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
import com.example.screendesign.activity.DesireShiftHandOverVerificationActivity
import com.example.screendesign.databinding.ActivityConfirmationAlreadyShiftHandOverBinding
import com.example.screendesign.viewmodel.ConfirmationAlreadyShiftHandOverViewModel
import com.example.screendesign.viewmodel.DesireShiftHandOverVerificationViewModel
import com.example.screendesign.viewmodel.DesireShiftHandOverViewModel

class ConfirmationAlreadyShiftHandOverFragment : Fragment() {

    private lateinit var binding:ActivityConfirmationAlreadyShiftHandOverBinding

    companion object {
        fun newInstance() = ConfirmationAlreadyShiftHandOverFragment()
    }

    private lateinit var viewModel: ConfirmationAlreadyShiftHandOverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[ConfirmationAlreadyShiftHandOverViewModel::class.java]

        binding = DataBindingUtil.inflate(inflater,
            R.layout.activity_confirmation_already_shift_hand_over, container, false)

        binding.shiftHandOverBtn.setOnClickListener {
            val intent = Intent(requireContext(), DesireShiftHandOverActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}