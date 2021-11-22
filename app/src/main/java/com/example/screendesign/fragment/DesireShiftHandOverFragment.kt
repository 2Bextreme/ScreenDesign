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
import com.example.screendesign.adapter.DesireShiftHandOverAdapter
import com.example.screendesign.activity.DesireShiftHandOverVerificationActivity
import com.example.screendesign.data.ShiftDate
import com.example.screendesign.databinding.DesireShiftHandOverFragmentBinding
import com.example.screendesign.viewmodel.DesireShiftHandOverVerificationViewModel
import com.example.screendesign.viewmodel.DesireShiftHandOverViewModel
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
    private lateinit var viewModel: DesireShiftHandOverVerificationViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.desire_shift_hand_over_fragment, container, false)

        //viewModelを使うためのセット的なもの
        val viewModel= DesireShiftHandOverViewModel()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        //


        //カレンダーの作成,および今月以外の日付を指定できないように設定する。
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
//        val shiftList :ArrayList<ShiftDate> = arrayListOf()
        //

        //呼び出された時点の年月を取得
        var i = 1
        val current = LocalDateTime.now()
        var formatter = DateTimeFormatter.ofPattern("yyyy")
        val year = current.format(formatter)
        formatter = DateTimeFormatter.ofPattern("MM")
        val month = current.format(formatter)
        //

        //recyclerViewで使う今月の日付リストを作成
        while (i <= calendar.getActualMaximum(Calendar.DATE)){
            val shiftDate = ShiftDate(
                year,
                month,
                i.toString(),
                isCheck = false
            )
            viewModel.shiftDate.add(shiftDate)
            i++
        }
        //

        //recyclerViewの構築・設定
        adapter = DesireShiftHandOverAdapter(
            layoutInflater,
            viewModel.shiftDate
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
        //

        //クリックした日付をrecyclerViewの一番上で表示するための日付を現在の日付で初期化
        formatter = DateTimeFormatter.ofPattern("dd")
        val day = Integer.parseInt(current.format(formatter))
        binding.calendarView.isFocusable = false
        var beforeSelectedDay = day
        //

        //クリックした日付をrecyclerViewの一番上で表示
        binding.calendarView.setOnDateChangeListener { view, _, _, dayOfMonth ->
            Log.d("date",dayOfMonth.toString())
            Log.d("bDate",beforeSelectedDay.toString())
            if (dayOfMonth > beforeSelectedDay){
                when(dayOfMonth){
                    viewModel.shiftDate.size -> binding.recyclerView.scrollToPosition(dayOfMonth - 1)
                    in viewModel.shiftDate.size-2 until viewModel.shiftDate.size ->binding.recyclerView.scrollToPosition(dayOfMonth)
                    else -> binding.recyclerView.scrollToPosition(dayOfMonth + 2)
                }
            }else{
                binding.recyclerView.scrollToPosition(dayOfMonth - 1)
            }
            beforeSelectedDay = dayOfMonth
            val tmpList = viewModel.shiftDate[dayOfMonth - 1]
            tmpList.isCheck = !tmpList.isCheck
            viewModel.shiftDate[dayOfMonth - 1] = tmpList
            adapter.notifyDataSetChanged()
        }
        //

        binding.verificationBtn.setOnClickListener {
            val completeShiftList = ArrayList<ShiftDate>()
            if(binding.radioButton2.isChecked){
                for(list in viewModel.shiftDate){
                    if(list.isCheck){
                        completeShiftList.add(list)
                    }
                }
            }else{
                for(list in viewModel.shiftDate){
                    if(!list.isCheck){
                        completeShiftList.add(list)
                    }
                }
            }

            val intent = Intent(requireContext(),DesireShiftHandOverVerificationActivity::class.java).apply {
                this.putExtra("shiftList",completeShiftList)
            }
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