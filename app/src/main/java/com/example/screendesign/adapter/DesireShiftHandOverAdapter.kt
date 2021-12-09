package com.example.screendesign.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.screendesign.R
import com.example.screendesign.data.ShiftDate
import com.example.screendesign.databinding.ShiftSubmissionDateItemBinding
import java.security.PrivateKey

class DesireShiftHandOverAdapter (
    private val layoutInflater: LayoutInflater,
    private val shiftList: ArrayList<ShiftDate>,
    private val callback:Callback
): RecyclerView.Adapter<DesireShiftHandOverAdapter.ViewHolder>() {

    interface Callback {
        fun deleteItem(shiftDate: ShiftDate)
    }

    override fun getItemCount() = shiftList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ShiftSubmissionDateItemBinding>(
            layoutInflater,
            R.layout.shift_submission_date_item,
            parent,
            false
        )
        return ViewHolder(binding,callback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shiftList[position])
    }

    class ViewHolder(
        private val binding: ShiftSubmissionDateItemBinding,
        private val callback:Callback
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(shiftDate: ShiftDate) {
            binding.checkBox.isChecked = shiftDate.isCheck
            binding.shiftDate = shiftDate
            binding.checkBox.setOnClickListener{
                callback.deleteItem(shiftDate)
            }
        }
    }
}