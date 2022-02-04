package com.example.screendesign.ui.configuration

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.example.screendesign.R
import com.example.screendesign.activity.LoginActivity
import com.example.screendesign.activity.MailAddressChangeActivity
import com.example.screendesign.activity.NotificationSettingActivity
import com.example.screendesign.activity.PasswordChangeActivity
import com.example.screendesign.databinding.ConfigurationFragmentBinding
import com.example.screendesign.repository.Repository
import com.example.screendesign.ui.home.HomeViewModel
import com.example.screendesign.ui.home.HomeViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ConfigurationFragment : Fragment() {

    companion object {
        fun newInstance() = ConfigurationFragment()
    }

    private lateinit var binding: ConfigurationFragmentBinding
    private lateinit var viewModel:ConfigurationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this,
            ConfigurationViewModelFactory(requireContext()))[ConfigurationViewModel::class.java]
        val repository = Repository(requireContext())
        binding = ConfigurationFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.checkNotificationBoolean()

        viewModel.notiBoolean.observe(viewLifecycleOwner,{
            viewModel.setNotificationBoolean()
        })



        //通知設定
//        binding.actionNotificationSetting.setOnClickListener{
//            startActivity(Intent(requireContext(), NotificationSettingActivity::class.java))
//        }

        //パスワード変更
        binding.actionPasswordChange.setOnClickListener {
            startActivity(Intent(requireContext(), PasswordChangeActivity::class.java))
        }

        //ログアウト
        binding.actionLogout.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val token = repository.get()
                if (token != "null"){
                    repository.logout(token)
                    repository.set("null")
                    startActivity(
                        Intent(requireContext(), LoginActivity::class.java).setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                }
            }
        }

        //メールアドレス変更
        binding.actionMailaddressChenge.setOnClickListener {
            startActivity(Intent(requireContext(), MailAddressChangeActivity::class.java))
        }

        return binding.root
    }
}