package com.example.completereadbookapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comic(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var detail: String
)
