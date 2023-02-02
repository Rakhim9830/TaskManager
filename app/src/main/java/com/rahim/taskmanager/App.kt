package com.rahim.taskmanager

import android.app.Application
import androidx.room.Room
import com.rahim.taskmanager.data.AppDatabase

class App : Application() {


    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,"database-name").allowMainThreadQueries().build()
    }

    companion object {
        lateinit var db: AppDatabase

    }
}
