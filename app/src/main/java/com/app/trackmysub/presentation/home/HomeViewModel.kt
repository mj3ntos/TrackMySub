package com.app.trackmysub.presentation.home

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.trackmysub.domain.model.SubscriptionFilter
import com.app.trackmysub.domain.model.entity.SubscriptionEntity
import com.app.trackmysub.domain.repository.UserRepository
import com.app.trackmysub.domain.usecase.subscription.SubscriptionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val subscriptionUseCases: SubscriptionUseCases,
    private val userRepository: UserRepository
) : ViewModel() {

    private var recentlyDeletedSubscription: SubscriptionEntity? = null

    private val filter = MutableStateFlow<SubscriptionFilter>(SubscriptionFilter.All)

    val user = userRepository.currentUser
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val state: StateFlow<HomeState> = filter
        .flatMapLatest { currentFilter ->
            subscriptionUseCases.getSubscriptions(currentFilter)
                .map { subscriptions ->
                    HomeState(
                        allSubscriptions = subscriptions,
                        isLoading = false
                    )
                }
                .onStart { emit(HomeState(isLoading = true)) }
                .catch {  e->
                    emit(HomeState(
                        isLoading = false,
                        error = e.message ?: "Unknown error"
                    ))
                }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeState(isLoading = true)
        )




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
                filter.value = event.filter
            }
        }
    }

    var currentDate = flow {
        emit(SimpleDateFormat("d MMMM, EEEE", Locale.getDefault()).format(Date()))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")



}