package com.rahim.taskmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rahim.taskmanager.model.TaskModel


@Database(entities = [TaskModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}