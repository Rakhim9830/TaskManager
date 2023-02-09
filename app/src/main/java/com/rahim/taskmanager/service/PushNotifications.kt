package com.rahim.taskmanager.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.NotificationParams
import com.google.firebase.messaging.RemoteMessage
import com.rahim.taskmanager.R
import com.rahim.taskmanager.service.PushNotifications.Companion.CHANNEL_ID


class PushNotifications:FirebaseMessagingService() {


      @SuppressLint("MissingPermission")
      @RequiresApi(Build.VERSION_CODES.O)
      internal fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage!!)
          val title = remoteMessage.notification?.title
          val text = remoteMessage.notification?.body
          val channel = NotificationChannel(CHANNEL_ID,"Heads up Notification", NotificationManager.IMPORTANCE_HIGH)
          getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
          val notification = Notification.Builder(this, CHANNEL_ID)

          notification.setContentTitle(title)
          notification.setContentText(text)
          notification.setSmallIcon(R.drawable.ic_notifications_black_24dp)
          notification.setAutoCancel(true)
          NotificationManagerCompat.from(this).notify(1, notification.build())
    }

    companion object{
        const val CHANNEL_ID = "HEADS_UP_NOTIFICATION"
    }
}