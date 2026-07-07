package com.app.trackmysub.presentation.home.components

import android.R.attr.maxLines
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.trackmysub.R
import com.app.trackmysub.domain.model.entity.SubscriptionEntity

@Composable
fun SubscriptionItem(
    subscription: SubscriptionEntity,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onItemLongClick: () -> Unit,
)
{

    ElevatedCard(
        modifier = modifier
            .padding(16.dp)
    ) {

        Column(
            modifier = modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            Image(
                painter = painterResource(
                    subscription.subscriptionIcon?: R.drawable.ic_default
                ),
                contentDescription = subscription.name,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(cornerRadius))


            )
            Spacer(modifier = modifier.height(8.dp))

            Text(
                text = subscription.name,
                //style = MaterialTheme.typography.,
                //color = MaterialTheme.colorScheme.,
                maxLines = 1
            )
            Spacer(modifier = modifier.height(12.dp))

            Text(
                text = "Expire date: ${subscription.renewalDate}",
                //style = MaterialTheme.typography.,
                //color = MaterialTheme.colorScheme.,
                maxLines = 1,
            )
            Spacer(modifier = modifier.height(2.dp))

            Text(
                text = "Renews in ${subscription.renewalDate} days",
                //style = MaterialTheme.typography.,
                //color = MaterialTheme.colorScheme.,
                maxLines = 1
            )
        }
    }

}

@Preview(backgroundColor = 0xFFF0EAE2, showBackground = true)
@Composable
private fun SubscriptionItemPrev() {
    SubscriptionItem(
        SubscriptionEntity(
            name = "Netflix",
            price = 30.50,
            billingCycle = "Monthly",
            subscriptionPlan = "Standard",
            renewalDate = 1234567890,
            createdAt = 1234567890,
            isActive = true,
            isNotificationActive = true,
            isSynced = false
        ),
        modifier = Modifier,
        cornerRadius = 10.dp,
        onItemLongClick = {

        }
    )
}


/*

To będzie potrzebne już bezpośrednio w home

LazyVerticalGrid(
columns = GridCells.Fixed(2),
contentPadding = PaddingValues(16.dp),
horizontalArrangement = Arrangement.spacedBy(16.dp),
verticalArrangement = Arrangement.spacedBy(12.dp),
modifier = modifier
) {
    items(subscription) { item ->  }
}

*/