package com.app.trackmysub.domain.usecase.subscription

import com.app.trackmysub.domain.model.entity.InvalidSubscriptionException
import com.app.trackmysub.domain.model.entity.SubscriptionEntity
import com.app.trackmysub.domain.repository.SubscriptionRepository

class AddSubscription (
    private val repository: SubscriptionRepository
) {

    @Throws(InvalidSubscriptionException::class)
    suspend operator fun invoke(subscription: SubscriptionEntity) {
        if(subscription.name.isBlank()) {
            throw InvalidSubscriptionException("The name of the subscription can't be empty.")
        }
        if(subscription.price == 0.0) {
            throw InvalidSubscriptionException("The price of the subscription must be greater than zero.")
        }
        if(subscription.billingCycle.isBlank()) {
            throw InvalidSubscriptionException("The billing cycle of the subscription can't be empty.")
        }
        if(subscription.renewalDate == 0L) {
            throw InvalidSubscriptionException("The renewal date of the subscription can't be empty.")
        }


        repository.saveSubscription(subscription)
    }
}
