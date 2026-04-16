package com.app.trackmysub.domain.usecase.subscription

import com.app.trackmysub.domain.model.entity.SubscriptionEntity
import com.app.trackmysub.domain.repository.SubscriptionRepository
import javax.inject.Inject

class DeleteSubscription @Inject constructor(
    private val repository: SubscriptionRepository
){
    suspend operator fun invoke(subscription: SubscriptionEntity) {
        repository.deleteSubscription(subscription)
    }

}
