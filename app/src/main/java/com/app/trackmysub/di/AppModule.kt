package com.app.trackmysub.di

import android.content.Context
import androidx.room.Room
import com.app.trackmysub.data.local.AppDatabase
import com.app.trackmysub.domain.repository.SubscriptionRepository
import com.app.trackmysub.domain.usecase.subscription.AddSubscription
import com.app.trackmysub.domain.usecase.subscription.DeleteSubscription
import com.app.trackmysub.domain.usecase.subscription.GetSubscriptions
import com.app.trackmysub.domain.usecase.subscription.SubscriptionUseCases
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

    @Provides
    @Singleton
    fun provideSubscriptionUseCases(repository: SubscriptionRepository): SubscriptionUseCases {
        return SubscriptionUseCases(
                deleteSubscription = DeleteSubscription(repository),
                getSubscriptions = GetSubscriptions(repository),
                addSubscription = AddSubscription(repository)
        )
    }

}