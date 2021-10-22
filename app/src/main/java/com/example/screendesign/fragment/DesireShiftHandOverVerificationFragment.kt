package com.example.screendesign.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.screendesign.R

class DesireShiftHandOverVerificationFragment : Fragment() {

    companion object {
        fun newInstance() = DesireShiftHandOverVerificationFragment()
    }

    private lateinit var viewModel: DesireShiftHandOverVerificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.desire_shift_hand_over_verification_fragment,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(DesireShiftHandOverVerificationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}