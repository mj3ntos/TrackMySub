package com.app.trackmysub.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey (autoGenerate = true)
    val userId: String? = null,
    val firstName: String,
    val lastName: String,
    val gender: List<String>,




)