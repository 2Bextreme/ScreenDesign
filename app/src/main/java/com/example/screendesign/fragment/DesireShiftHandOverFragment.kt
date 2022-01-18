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
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.screendesign.R
import com.example.screendesign.adapter.DesireShiftHandOverAdapter
import com.example.screendesign.activity.DesireShiftHandOverVerificationActivity
import com.example.screendesign.data.ShiftDate
import com.example.screendesign.databinding.DesireShiftHandOverFragmentBinding
import com.example.screendesign.viewmodel.DesireShiftHandOverVerificationViewModel
import com.example.screendesign.viewmodel.DesireShiftHandOverViewModel
import com.example.screendesign.viewmodel.ThisMonthShiftConfirmationScreenViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class DesireShiftHandOverFragment : Fragment() {

    companion object {
        fun newInstance() = DesireShiftHandOverFragment()
    }

    private lateinit var binding:DesireShiftHandOverFragmentBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DesireShiftHandOverAdapter
    private lateinit var viewModel: DesireShiftHandOverViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.desire_shift_hand_over_fragment, container, false)

        //viewModelを使うためのセット的なもの
        viewModel = ViewModelProvider(this)[DesireShiftHandOverViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        //


        //カレンダーの作成,および今月以外の日付を指定できないように設定する。
        var calendar = Calendar.getInstance()
        calendar[Calendar.MONTH] = calendar.get(Calendar.MONTH) + 1
        calendar[Calendar.DATE] = 1
        calendar[Calendar.HOUR_OF_DAY] = 0
        val startOfMonth = calendar.timeInMillis
        calendar = Calendar.getInstance()
        calendar[Calendar.MONTH] = calendar.get(Calendar.MONTH) + 1
        calendar[Calendar.DATE] =  calendar.getActualMaximum(Calendar.DATE)
        calendar[Calendar.HOUR_OF_DAY] = 23
        val endOfMonth = calendar.timeInMillis
        binding.calendarView.minDate = startOfMonth
        binding.calendarView.maxDate = endOfMonth
        //

        val openDetailListener = object : DesireShiftHandOverAdapter.Callback {
            override fun deleteItem(shiftDate: ShiftDate) {
                viewModel.shiftDate.remove(shiftDate)
                adapter.notifyDataSetChanged()
            }
        }

        //recyclerViewの構築・設定
        adapter = DesireShiftHandOverAdapter(
            layoutInflater,
            viewModel.shiftDate,
            openDetailListener
        )
        adapter.notifyDataSetChanged()

        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.recyclerView.also {
            it.layoutManager = layoutManager
            it.adapter = adapter
        }
        //

        //クリックした日付をrecyclerViewに追加
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            Log.d("date",dayOfMonth.toString())
            val shiftDate = ShiftDate(
                year = year.toString(),
                month = month.inc().toString(),
                day = dayOfMonth.toString(),
                isCheck = true
            )
            if(viewModel.shiftDate.isEmpty() || !viewModel.shiftDate.contains(shiftDate)){
                viewModel.shiftDate.add(shiftDate)
            }else{
                viewModel.shiftDate.remove(shiftDate)
            }
            viewModel.shiftDate.sortBy { it.day.toInt() }
            adapter.notifyDataSetChanged()
        }
        //

        binding.verificationBtn.setOnClickListener {
            val completeShiftList = ArrayList<ShiftDate>()
            for(list in viewModel.shiftDate){
                if(list.isCheck){
                    completeShiftList.add(list)
                }
            }

            val intent = Intent(requireContext(),DesireShiftHandOverVerificationActivity::class.java).apply {
                this.putExtra("shiftList",completeShiftList)
            }
            startActivity(intent)
        }


        return binding.root
    }
}