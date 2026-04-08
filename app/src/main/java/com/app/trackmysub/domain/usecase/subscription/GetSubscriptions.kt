package com.app.trackmysub.domain.usecase.subscription

import com.app.trackmysub.domain.repository.SubscriptionRepository
import jakarta.inject.Inject

class GetSubscriptions @Inject constructor(
    private val repository: SubscriptionRepository
){


}