package com.rahim.taskmanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    var title:String? = null,
    var desc:String? = null

) : Serializable
