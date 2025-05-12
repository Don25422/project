package com.don.hustletracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "new_earnings")
data class NewEarning(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val description: String,
    val amount: Double
)
