package com.app.trackmysub.di

import android.content.Context
import androidx.room.Room
import com.app.trackmysub.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "subscriptions_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSubscriptionDao(db: AppDatabase) = db.subscriptionDao()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase) = db.userDao()

}