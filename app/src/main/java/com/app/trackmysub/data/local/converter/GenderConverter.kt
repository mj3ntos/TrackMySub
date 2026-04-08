package com.app.trackmysub.data.local.converter

import androidx.room.TypeConverter
import com.app.trackmysub.data.local.enums.Gender

class GenderConverter {

    @TypeConverter
    fun fromGender(value: Gender): String = value.name

    @TypeConverter
    fun toGender(value: String): Gender = Gender.valueOf(value)
}