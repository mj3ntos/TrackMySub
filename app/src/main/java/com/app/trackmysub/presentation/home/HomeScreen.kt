package com.app.trackmysub.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.trackmysub.presentation.home.components.SubscriptionItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val user by viewModel.user.collectAsStateWithLifecycle()
    val currentDate by viewModel.currentDate.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {
                Icon(Icons.Rounded.Add, "Add subscription")
            }
        }
    ) { padding ->
        Column(

        ) {
            Row(
                modifier = Modifier
                    .padding(padding)
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Hi, ${user?.firstName ?: "User"}",
                        modifier = Modifier
                            .padding(16.dp)
                    )

                    Text(
                        text = "$currentDate",
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            Text(
                text = "Current Subscriptions",
                modifier = Modifier
                    .padding(16.dp)
            )

            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                state.error != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Error: ${state.error}")
                    }
                }
                state.isEmpty -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "No subscriptions found")
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(padding)
                    ) {
                        items(state.allSubscriptions, key = { it.subscriptionId }) { subscription ->
                            SubscriptionItem(
                                subscription = subscription,
                                onItemLongClick = { TODO() },
                                modifier = Modifier
                                    .clickable {

                                    }
                            )

                        }
                    }
                }
            }



        }


    }
}

@Preview(backgroundColor = 0xFFF0EAE2, showBackground = true)
@Composable
private fun HomeScreenPreview(){
    HomeScreen()
}