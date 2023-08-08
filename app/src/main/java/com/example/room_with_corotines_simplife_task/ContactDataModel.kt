package com.example.room_with_corotines_simplife_task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactDataModel(
    @PrimaryKey(autoGenerate = false)
    var id:Int,
    var name:String,

    var phone:String)