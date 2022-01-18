package com.example.screendesign.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.screendesign.R
import com.example.screendesign.activity.TopPageActivity
import com.example.screendesign.adapter.DesireShiftHandOverVerificationAdapter
import com.example.screendesign.data.ShiftDate
import com.example.screendesign.databinding.DesireShiftHandOverVerificationFragmentBinding
import com.example.screendesign.repository.Repository
import com.example.screendesign.viewmodel.DesireShiftHandOverVerificationViewModel
import com.example.screendesign.viewmodel.PasswordChangeViewModel
import com.example.screendesign.viewmodelfactory.DesireShiftHandOverVerificationViewModelFactory
import com.example.screendesign.viewmodelfactory.PasswordChangeViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


class DesireShiftHandOverVerificationFragment : Fragment() {

    companion object {
        fun newInstance() = DesireShiftHandOverVerificationFragment()
    }

    interface Callback {
        fun clickFixBtn()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = context as? Callback
        if (callback == null) {
            throw ClassCastException("$context must implement Callback")
        }
    }

    private var callback: Callback? = null
    private lateinit var binding: DesireShiftHandOverVerificationFragmentBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DesireShiftHandOverVerificationAdapter
    private lateinit var viewModel:DesireShiftHandOverVerificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.desire_shift_hand_over_verification_fragment, container, false
        )



        //viewModelを使うためのセット的なもの
        viewModel = ViewModelProvider(this, DesireShiftHandOverVerificationViewModelFactory(requireContext()))[DesireShiftHandOverVerificationViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        //

        viewModel.shiftList = requireArguments().getSerializable("FILE_KEY") as ArrayList<ShiftDate>

        //recyclerViewの構築・設定
        adapter = DesireShiftHandOverVerificationAdapter(
            layoutInflater,
            viewModel.shiftList
        )

        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.recyclerview.also {
            it.layoutManager = layoutManager
            it.adapter = adapter
        }
        //

        binding.fixBtn.setOnClickListener {
            callback?.clickFixBtn()
        }

        Log.d("shiftList",viewModel.shiftList.toString())

        if (viewModel.shiftList.isEmpty()){
            binding.verificationBtn2.isEnabled = false
        }else{
            binding.verificationBtn2.setOnClickListener {
                viewModel.ShiftHandOver()
            }
        }

        viewModel.isLog.observe(viewLifecycleOwner,{
            runSnackBar(it)
            if(it == 1){
                val intent = Intent(requireContext(), TopPageActivity::class.java)
                startActivity(intent)
            }

        })

        return binding.root
    }
    private fun runSnackBar(log:Int){
        when(log){
            1 -> Snackbar.make(binding.root, "シフトを提出しました。", Snackbar.LENGTH_SHORT).show()
            2 -> Snackbar.make(binding.root, "シフトが提出できませんでした。", Snackbar.LENGTH_SHORT).show()
            else -> return
        }
    }
}