package com.app.trackmysub.presentation.home

import androidx.lifecycle.ViewModel
import com.app.trackmysub.domain.repository.SubscriptionRepository
import com.app.trackmysub.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val subscriptionRepository: SubscriptionRepository
) : ViewModel() {

    val currentUser = userRepository.currentUser
    val allSubscriptions = subscriptionRepository.allSubscriptions



}