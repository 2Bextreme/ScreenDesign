package com.example.screendesign.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.screendesign.R
import com.example.screendesign.data.ShiftDate
import com.example.screendesign.databinding.ShiftSubmissionDateItemBinding

class DesireShiftHandOverVerificationAdapter (
    private val layoutInflater: LayoutInflater,
    private val shiftList: List<ShiftDate>
): RecyclerView.Adapter<DesireShiftHandOverVerificationAdapter.ViewHolder>() {

    override fun getItemCount() = shiftList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ShiftSubmissionDateItemBinding>(
            layoutInflater,
            R.layout.shift_submission_date_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shiftList[position])
    }

    class ViewHolder(
        private val binding: ShiftSubmissionDateItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(shiftDate: ShiftDate) {
            binding.checkBox.isChecked = shiftDate.isCheck
            binding.shiftDate = shiftDate
        }
    }
}