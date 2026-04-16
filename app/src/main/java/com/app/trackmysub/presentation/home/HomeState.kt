package com.app.trackmysub.presentation.home

import com.app.trackmysub.domain.model.entity.SubscriptionEntity

data class HomeState(
    val allSubscriptions: List<SubscriptionEntity> = emptyList()
)
