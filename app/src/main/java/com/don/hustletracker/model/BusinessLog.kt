package com.don.hustletracker.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "business_logs")
data class BusinessLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val amount: Double,
    val date: String
)
