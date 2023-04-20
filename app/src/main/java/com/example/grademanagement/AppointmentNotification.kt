package com.example.grademanagement

import android.annotation.SuppressLint
import android.app.*
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Color.GREEN
import android.graphics.Color.pack
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.os.Build.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews

class AppointmentNotification : AppCompatActivity(){
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder : Notification.Builder
    lateinit var btnnotify:Button
    lateinit var remoteCollapseViews: RemoteViews
    lateinit var remoteExpandViews : RemoteViews
    lateinit var pendingIntent: PendingIntent
    lateinit var soundUri: Uri
    lateinit var audioAttributes: AudioAttributes
    lateinit var remoteInput: RemoteInput
    private val channelId = "My channel id"
    private val description = "Your Appointment Booked"
    private val title = "Appointment"

    val mykey = "Remote key"
    val notificationid = 1234


    @SuppressLint("RemoteViewLayout")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grademanagement)

        btnnotify = findViewById<Button>(R.id.mentorbut)

        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btnnotify.setOnClickListener{
            val intent = Intent(this , AppointmentNotification:: class.java)
            pendingIntent = PendingIntent.getActivity(this , 0 ,intent,PendingIntent.FLAG_MUTABLE)


            remoteCollapseViews = RemoteViews(packageName,R.layout.activity_grade_management_result1)
            remoteExpandViews = RemoteViews(packageName,R.layout.activity_grade_management_result1)
            myNotificationChannel()

            remoteInput =RemoteInput.Builder(mykey).setLabel("Replying").build()

            val action : Notification.Action = Notification.Action.Builder(R.drawable.ic_baseline_arrow_right_alt_24,"Reply",pendingIntent).addRemoteInput(remoteInput).build()
            builder.addAction(action)

            notificationManager.notify(notificationid,builder.build())



        }
    }

    private fun myNotificationChannel() {
        if(VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationChannel= NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor= Color.GREEN
            notificationChannel.enableVibration(false)

            notificationManager.createNotificationChannel(notificationChannel)

            builder=Notification.Builder(this , channelId)
                .setSmallIcon(R.drawable.reddish_background)
                .setContentTitle(description)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.dashboard))
                .setContentIntent(pendingIntent)

                .setAutoCancel(true)
        }
        else{
            builder=Notification.Builder(this)
                .setSmallIcon(R.drawable.reddish_background)
                .setContentTitle(title)
                .setContentText(description)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.dashboard))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)


        }

    }
}

private fun NotificationManager.notify(build: Any) {
    TODO("Not yet implemented")
}