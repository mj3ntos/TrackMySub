package com.app.trackmysub.domain.usecase.subscription

data class SubscriptionUseCases (
    val deleteSubscription: DeleteSubscription,
    val getSubscriptions: GetSubscriptions,
    val addSubscription: AddSubscription
)
