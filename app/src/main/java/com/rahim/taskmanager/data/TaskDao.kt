package com.rahim.taskmanager.data

import androidx.room.*
import com.rahim.taskmanager.model.TaskModel
@Dao
interface TaskDao {

    @Query("SELECT * FROM taskmodel")
    fun getAll(): List <TaskModel>

    @Insert
    fun insert(taskData: TaskModel)

    @Delete
    fun delete(taskData: TaskModel)

    @Update
    fun update(taskData: TaskModel)



}