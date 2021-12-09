package com.example.screendesign.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.screendesign.R
import com.example.screendesign.databinding.ActivityTopPageBinding
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.*

class TopPageActivity : AppCompatActivity() {

    companion object{
        const val CHANNEL_ID = "2134114312412"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityTopPageBinding
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTopPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarTopPage.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_top_page)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )

        createNotificationChannel() //通知チャンネルの作成

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    //activity_top_page.xmlにメニュー(top_page)を追加
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_page, menu)
        return true
    }
    //

    //メニューをクリックしたときの処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //通知設定
            R.id.action_notification_setting -> {
                startActivity(Intent(applicationContext,NotificationSettingActivity::class.java))
            }

            //パスワード変更
            R.id.action_password_change -> {
                startActivity(Intent(applicationContext,PasswordChangeActivity::class.java))
            }

            //ログアウト
            R.id.action_logout -> {
                repository = Repository(applicationContext)
                CoroutineScope(Dispatchers.IO).launch {
                    val token = repository.get()
                    if (token != "null"){
                        repository.logout(token)
                        repository.set("null")
                        startActivity(Intent(applicationContext,LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                    }
                }
            }

            //メールアドレス変更
            R.id.action_mailaddress_chenge -> {
                startActivity(Intent(applicationContext,MailAddressChangeActivity::class.java))
            }

            else -> return super.onOptionsItemSelected(item)
        }
        return false
    }
    //

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "テストチャンネル"
            val descriptionText = "テスト通知"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_top_page)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}