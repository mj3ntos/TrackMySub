package com.app.trackmysub.domain.usecase.subscription

import com.app.trackmysub.domain.model.entity.SubscriptionEntity
import com.app.trackmysub.domain.repository.SubscriptionRepository
import com.app.trackmysub.presentation.home.SubscriptionFilter
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSubscriptions @Inject constructor(
    private val repository: SubscriptionRepository
){

    operator fun invoke(filter: SubscriptionFilter = SubscriptionFilter.All): Flow<List<SubscriptionEntity>> {
        return repository.allSubscriptions.map { subscriptions ->
            val sorted = subscriptions.sortedBy { it.renewalDate }
            when (filter) {
                is SubscriptionFilter.All -> sorted
            }
        }
    }
}