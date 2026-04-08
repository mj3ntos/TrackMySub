package com.app.trackmysub.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.trackmysub.data.local.enums.Gender
import java.util.UUID

@Entity (tableName = "users")
data class UserEntity (
    @PrimaryKey
    val userId: String = "local_user",
    val firstName: String,
    val lastName: String?,
    val gender: Gender = Gender.NOT_SPECIFIED,
    val birthDate: Long,
    val profileImg: String? = null

)