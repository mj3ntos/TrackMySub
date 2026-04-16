package com.app.trackmysub.domain.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity (
    tableName = "subscriptions",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["ownerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("ownerId")]
)

data class SubscriptionEntity (
    @PrimaryKey
    val subscriptionId: String = UUID.randomUUID().toString(),
    val ownerId: String = "local_user",
    val subscriptionIcon: String? = null,
    val name: String,
    val price: Double,
    val currency: String = "$",
    val billingCycle: String,
    val subscriptionPlan: String?,
    val renewalDate: Long,
    val createdAt: Long,
    val isActive: Boolean = true,
    val isNotificationActive: Boolean = true,
    val notificationDaysBefore: Int = 2,
    val note: String? = null,
    val isSynced: Boolean = false

)

class InvalidSubscriptionException(message: String): Exception(message)