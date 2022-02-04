package com.example.screendesign.fragment

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.screendesign.R
import com.example.screendesign.activity.DesireShiftHandOverActivity
import com.example.screendesign.activity.DesireShiftHandOverVerificationActivity
import com.example.screendesign.adapter.ToMonthShiftListAdapter
import com.example.screendesign.databinding.ActivityConfirmationAlreadyShiftHandOverBinding
import com.example.screendesign.viewmodel.ConfirmationAlreadyShiftHandOverViewModel
import com.example.screendesign.viewmodel.DesireShiftHandOverVerificationViewModel
import com.example.screendesign.viewmodel.DesireShiftHandOverViewModel
import com.example.screendesign.viewmodelfactory.ConfirmationAlreadyShiftHandOverViewModelFactory
import com.example.screendesign.viewmodelfactory.DesireShiftHandOverVerificationViewModelFactory
import java.util.*

class ConfirmationAlreadyShiftHandOverFragment : Fragment() {

    private lateinit var binding:ActivityConfirmationAlreadyShiftHandOverBinding

    companion object {
        fun newInstance() = ConfirmationAlreadyShiftHandOverFragment()
    }

    private lateinit var viewModel: ConfirmationAlreadyShiftHandOverViewModel
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: ToMonthShiftListAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(
            this, ConfirmationAlreadyShiftHandOverViewModelFactory(requireContext())
        )[ConfirmationAlreadyShiftHandOverViewModel::class.java]

        val calendar = Calendar.getInstance()
        viewModel.month = calendar.get(Calendar.MONTH) + 2
        if(viewModel.month == 13){
            viewModel.month = 1
            viewModel.year  = calendar.get(Calendar.YEAR) + 1
        }else{
            viewModel.year  = calendar.get(Calendar.YEAR)
        }


        binding = DataBindingUtil.inflate(inflater,
            R.layout.activity_confirmation_already_shift_hand_over, container, false)

        binding.shiftHandOverBtn.setOnClickListener {
            val intent = Intent(requireContext(), DesireShiftHandOverActivity::class.java)
            startActivity(intent)
        }

        //recyclerViewの構築・設定
        adapter = ToMonthShiftListAdapter(
            layoutInflater,
            viewModel.shiftList
        )

        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.recyclerView3.also {
            it?.layoutManager = layoutManager
            it?.adapter = adapter
        }
        //

        viewModel.isLog.observe(viewLifecycleOwner,{
            Log.d("Log",it.toString())
            if(it == 0){
                adapter.notifyDataSetChanged()
                Log.d("リスト更新","")
            }
        })

        viewModel.getSubmittedShift()



        return binding.root
    }
}