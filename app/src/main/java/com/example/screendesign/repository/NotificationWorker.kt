package com.example.screendesign.repository

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.screendesign.R
import com.example.screendesign.activity.DesireShiftHandOverActivity
import com.example.screendesign.activity.TopPageActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationWorker (appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams){

    private lateinit var repository:NotificationRepository

    override fun doWork(): Result {
        val textTitle = "シフト提出期限が近いです"
        val textContent = "シフトを提出してください。"
        val notificationId = 1

        val intent = Intent(applicationContext, DesireShiftHandOverActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        repository = NotificationRepository(applicationContext)
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(applicationContext, TopPageActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        fun popNotification() { with(NotificationManagerCompat.from(applicationContext)) { notify(notificationId, builder.build())
                } }
        popNotification()
        return Result.success()
    }
}