package com.app.trackmysub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.trackmysub.data.local.converter.GenderConverter
import com.app.trackmysub.data.local.dao.SubscriptionDao
import com.app.trackmysub.data.local.dao.UserDao
import com.app.trackmysub.domain.model.entity.SubscriptionEntity
import com.app.trackmysub.domain.model.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        SubscriptionEntity::class
               ],
    version = 1
)

@TypeConverters(GenderConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun subscriptionDao(): SubscriptionDao
}