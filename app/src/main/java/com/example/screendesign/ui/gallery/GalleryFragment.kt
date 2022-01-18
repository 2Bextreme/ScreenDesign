package com.example.screendesign.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.screendesign.adapter.ToMonthShiftListAdapter
import com.example.screendesign.databinding.FragmentGalleryBinding
import java.util.*

class GalleryFragment : Fragment() {
    private lateinit var viewModel: GalleryViewModel

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: ToMonthShiftListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this, GalleryViewModelFactory(requireContext()))[GalleryViewModel::class.java]
        val calendar = Calendar.getInstance()
        viewModel.month = calendar.get(Calendar.MONTH) + 1
        viewModel.year  = calendar.get(Calendar.YEAR)
        Log.d("month",(calendar.get(Calendar.MONTH) + 1).toString())
        Log.d("month",calendar.get(Calendar.YEAR).toString())

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //recyclerViewの構築・設定
        adapter = ToMonthShiftListAdapter(
            layoutInflater,
            viewModel.shiftList.value
        )

        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.recyclerView2.also {
            it?.layoutManager = layoutManager
            it?.adapter = adapter
        }
        //

        viewModel.getToMonthShift()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}