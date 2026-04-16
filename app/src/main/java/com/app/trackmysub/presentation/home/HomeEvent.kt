package com.app.trackmysub.presentation.home

import com.app.trackmysub.domain.model.entity.SubscriptionEntity

sealed class HomeEvent {
    data class DeleteSubscription(val subscription: SubscriptionEntity) : HomeEvent()
    object RestoreSubscription : HomeEvent()
    data class FilterSubscriptions(val filter: SubscriptionFilter) : HomeEvent()

}

sealed class SubscriptionFilter {
    object All : SubscriptionFilter()
}