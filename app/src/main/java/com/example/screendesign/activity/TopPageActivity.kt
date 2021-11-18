package com.example.screendesign.activity

import android.content.Intent
import android.os.Bundle
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TopPageActivity : AppCompatActivity() {

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
                GlobalScope.launch {
                    val token = repository.get()
                    if (token != "null"){
                        repository.logout(token)
                        repository.set("null")
                        startActivity(Intent(applicationContext,LoginActivity::class.java))
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_top_page)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}