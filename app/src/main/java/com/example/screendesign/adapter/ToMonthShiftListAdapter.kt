package com.example.screendesign.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.screendesign.R
import com.example.screendesign.data.ToMonthShitList
import com.example.screendesign.databinding.ShiftListItemBinding

class ToMonthShiftListAdapter(
    private val layoutInflater: LayoutInflater,
    private val shiftList: ToMonthShitList?
): RecyclerView.Adapter<ToMonthShiftListAdapter.ViewHolder>() {

    override fun getItemCount() = shiftList?.days?.size ?:0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ShiftListItemBinding>(
            layoutInflater,
            R.layout.shift_list_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        shiftList?.days?.get(position)?.let { holder.bind(it) }
    }

    class ViewHolder(
        private val binding: ShiftListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shiftDay: Int) {

        }
    }
}