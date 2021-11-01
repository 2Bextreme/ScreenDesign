package com.example.screendesign.fragment


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
import com.example.screendesign.activity.DesireShiftHandOverAdapter
import com.example.screendesign.activity.DesireShiftHandOverVerificationActivity
import com.example.screendesign.data.ShiftDate
import com.example.screendesign.databinding.DesireShiftHandOverFragmentBinding
import com.example.screendesign.viewmodel.DesireShiftHandOverVerificationViewModel
import java.util.*
import kotlin.collections.ArrayList


class DesireShiftHandOverFragment : Fragment() {

    companion object {
        fun newInstance() = DesireShiftHandOverFragment()
    }

    private lateinit var binding:DesireShiftHandOverFragmentBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DesireShiftHandOverAdapter
    private lateinit var viewModel: DesireShiftHandOverVerificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.desire_shift_hand_over_fragment, container, false)

        var calendar = Calendar.getInstance()
        calendar[Calendar.DATE] = calendar.getActualMaximum(Calendar.DATE)
        calendar[Calendar.HOUR_OF_DAY] = 23
        val endOfMonth = calendar.timeInMillis
        calendar = Calendar.getInstance()
        calendar[Calendar.DATE] = 1
        calendar[Calendar.HOUR_OF_DAY] = 0
        val startOfMonth = calendar.timeInMillis
        binding.calendarView.minDate = startOfMonth
        binding.calendarView.maxDate = endOfMonth
        val shiftList :ArrayList<ShiftDate> = arrayListOf()

        var i = 1
        while (i < calendar.getActualMaximum(Calendar.DATE)){
            val shiftDate = ShiftDate(
                Calendar.YEAR.toString(),
                Calendar.MONTH.toString(),(i + 1).toString())
            shiftList.add(shiftDate)
            i++
        }

        adapter = DesireShiftHandOverAdapter(
            layoutInflater,
            shiftList,
            calendar.getActualMaximum(Calendar.DATE)
        )

        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.recyclerView.also {
            it.layoutManager = layoutManager
            it.adapter = adapter
        }

        binding.calendarView.setOnDateChangeListener { view, _, _, dayOfMonth ->
            Log.d("date",dayOfMonth.toString())
        }

        binding.verificationBtn.setOnClickListener {
            val intent = Intent(requireContext(),DesireShiftHandOverVerificationActivity::class.java)
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