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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.screendesign.R
import com.example.screendesign.activity.TopPageActivity
import com.example.screendesign.adapter.DesireShiftHandOverVerificationAdapter
import com.example.screendesign.data.ShiftDate
import com.example.screendesign.databinding.DesireShiftHandOverVerificationFragmentBinding
import com.example.screendesign.repository.Repository
import com.example.screendesign.viewmodel.DesireShiftHandOverVerificationViewModel
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
    private lateinit var repository : Repository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.desire_shift_hand_over_verification_fragment, container, false
        )

        val shiftList = requireArguments().getSerializable("FILE_KEY") as ArrayList<ShiftDate>

        //viewModelを使うためのセット的なもの
        val viewModel = DesireShiftHandOverVerificationViewModel()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        //

        //recyclerViewの構築・設定
        adapter = DesireShiftHandOverVerificationAdapter(
            layoutInflater,
            shiftList
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
        Log.d("shiftList",shiftList.toString())
        if (shiftList.isEmpty()){
            binding.verificationBtn2.isEnabled = false
        }else{
            binding.verificationBtn2.setOnClickListener {
                val year = shiftList.get(index = 0).year
                val month = shiftList.get(index = 0).month
                val days = ArrayList<Int>()
                shiftList.forEach {
                    days.add(it.day.toInt())
                }
                repository = Repository(requireContext())

                CoroutineScope(Dispatchers.IO).launch {
                    repository.deleteShift()
                    val result = repository.shiftHandOver(
                        year = year.toInt(),
                        month = month.toInt(),
                        days = days.toList()
                    ).result
                    Log.d("result",result.toString())
                    Log.d("shiftList", "${year}/${month}/${days}")
                    if (result){
                        val intent = Intent(requireContext(), TopPageActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }

        return binding.root
    }
}