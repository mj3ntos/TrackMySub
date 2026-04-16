package com.app.trackmysub.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.trackmysub.domain.model.entity.SubscriptionEntity
import com.app.trackmysub.domain.usecase.subscription.SubscriptionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val subscriptionUseCases: SubscriptionUseCases
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state


    private var recentlyDeletedSubscription: SubscriptionEntity? = null

    private var getSubscriptionsJob: Job? = null

    init {
        getSubscriptions()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.DeleteSubscription -> {
                viewModelScope.launch {
                    subscriptionUseCases.deleteSubscription(event.subscription)
                    recentlyDeletedSubscription = event.subscription
                }
            }

            is HomeEvent.RestoreSubscription -> {
                viewModelScope.launch {
                    subscriptionUseCases.addSubscription(
                        recentlyDeletedSubscription ?: return@launch
                        )
                    recentlyDeletedSubscription = null
                }
            }

            is HomeEvent.FilterSubscriptions -> {
                getSubscriptions(event.filter)
            }
        }
    }


    private fun getSubscriptions(filter: SubscriptionFilter = SubscriptionFilter.All) {
        getSubscriptionsJob?.cancel()
        getSubscriptionsJob = subscriptionUseCases.getSubscriptions()
            .onEach { subscriptions ->
                _state.value = state.value.copy(
                    allSubscriptions = subscriptions
            )
        }.launchIn(viewModelScope)
    }
}