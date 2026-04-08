package com.app.trackmysub.domain.repository

import com.app.trackmysub.data.local.dao.SubscriptionDao
import com.app.trackmysub.domain.model.entity.SubscriptionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubscriptionRepository @Inject constructor(
    private val subscriptionDao: SubscriptionDao
){

    val allSubscriptions = subscriptionDao.getAllSubscriptions()

    suspend fun saveSubscription(subscription: SubscriptionEntity) {
        subscriptionDao.upsertSubscription(subscription)
    }

    suspend fun deleteSubscription(subscription: SubscriptionEntity) {
        subscriptionDao.deleteSubscription(subscription)
    }

    fun getSubscriptionById(subscriptionId: String): Flow<SubscriptionEntity?> {
        return subscriptionDao.getSubscriptionById(subscriptionId)
    }


}