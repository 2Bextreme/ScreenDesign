package com.example.screendesign.ui.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.screendesign.activity.ConfirmationAlreadyShiftHandOverActivity
import com.example.screendesign.activity.DesireShiftHandOverActivity
import com.example.screendesign.activity.TopPageActivity
import com.example.screendesign.databinding.FragmentHomeBinding
import com.example.screendesign.repository.NotificationWorker
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = Repository(requireContext())
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.confirmationAlreadyShiftHandOverTransitionBtn.setOnClickListener {
            startActivity(Intent(requireContext(),ConfirmationAlreadyShiftHandOverActivity::class.java))
        }

        binding.desireShiftHandOverTransitionBtn.setOnClickListener {
            startActivity(Intent(requireContext(),DesireShiftHandOverActivity::class.java))
        }

        val uploadWorkRequest: WorkRequest =
            PeriodicWorkRequest.Builder(NotificationWorker::class.java,16, TimeUnit.MINUTES).build()


        CoroutineScope(Dispatchers.Main).launch {
            Log.d("Fragment","HomeFragment")
            Log.d("workId",repository.getWorkId())
            if (repository.getWorkId() == "null"){
                Log.d("通知","通知は起動していません。")
            }else{
                Log.d("通知","通知は起動しています。")
            }
        }


        binding.notiStart.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                if (repository.getWorkId() == "null"){
                    //通知を起動
                    Log.d("通知","通知は起動していません。")
                    WorkManager
                        .getInstance(requireContext())
                        .enqueue(uploadWorkRequest)
                    repository.setWorkId(uploadWorkRequest.id.toString())
                    Log.d("通知","通知を起動し,WorkIDを登録しました")
                    Log.d("workId",repository.getWorkId())
                }else{
                    Log.d("通知","通知は起動しています。")
                    Log.d("workId",repository.getWorkId())
                }
            }
        }

        binding.notiEnd.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                if (repository.getWorkId() == "null"){
                    //通知を起動
                    Log.d("通知","通知は起動していません。")
                    Log.d("workId",repository.getWorkId())
                }else{
                    Log.d("通知","通知は起動しています。")
                    Log.d("workId",repository.getWorkId())
                    WorkManager
                        .getInstance(requireContext())
                        .cancelWorkById(UUID.fromString(repository.getWorkId()))
                    Log.d("通知","通知をキャンセルしました。")
                    repository.setWorkId("null")
                    Log.d("workId",repository.getWorkId())
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

