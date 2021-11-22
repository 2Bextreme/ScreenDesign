package com.example.screendesign.repository

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.screendesign.R
import com.example.screendesign.activity.DesireShiftHandOverActivity
import com.example.screendesign.activity.TopPageActivity

class NotificationWorker (appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams){

    override fun doWork(): Result {

        val textTitle = "シフト提出期限が近いです"
        val textContent = "シフトを提出してください。"
        val notificationId = 1

        val intent = Intent(applicationContext, DesireShiftHandOverActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(applicationContext, TopPageActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        fun popNotification() {
            with(NotificationManagerCompat.from(applicationContext)) {
                notify(notificationId, builder.build())
            }
        }
        popNotification()
        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
    }
}