package com.don.hustletracker.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_data")
data class Health(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val activity: String,
    val durationMinutes: Int
) {


}



