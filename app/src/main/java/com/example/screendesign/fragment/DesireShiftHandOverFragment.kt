package com.example.screendesign.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.screendesign.R
import com.example.screendesign.databinding.DesireShiftHandOverFragmentBinding
import com.example.screendesign.viewmodel.MainViewModel

class DesireShiftHandOverFragment : Fragment() {

    private lateinit var binding:DesireShiftHandOverFragmentBinding

    companion object {
        fun newInstance() = DesireShiftHandOverFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.desire_shift_hand_over_fragment, container, false)

        binding.verificationBtn.setOnClickListener {

        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        }
    }