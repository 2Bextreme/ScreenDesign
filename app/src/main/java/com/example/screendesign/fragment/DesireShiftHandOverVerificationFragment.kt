package com.example.screendesign.fragment

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.screendesign.R
import com.example.screendesign.adapter.DesireShiftHandOverAdapter
import com.example.screendesign.activity.TopPageActivity
import com.example.screendesign.adapter.DesireShiftHandOverVerificationAdapter
import com.example.screendesign.data.ShiftDate
import com.example.screendesign.databinding.DesireShiftHandOverVerificationFragmentBinding
import com.example.screendesign.viewmodel.DesireShiftHandOverVerificationViewModel


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
    private lateinit var viewModel: DesireShiftHandOverVerificationViewModel
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DesireShiftHandOverVerificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.desire_shift_hand_over_verification_fragment, container, false)

        val shiftList = requireArguments().getSerializable("FILE_KEY") as ArrayList<ShiftDate>

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

        binding.verificationBtn2.setOnClickListener {
            val intent = Intent(requireContext(),TopPageActivity::class.java)
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