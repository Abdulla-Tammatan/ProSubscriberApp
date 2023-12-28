package com.example.prosubscriberapp.service

import com.example.prosubscriberapp.config.Subscriptions
import com.example.prosubscriberapp.model.Subscription
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


class SubscriptionService {

    // Function to get all active subscriptions for a user
    fun getAllActiveSubscriptions(userId: Int): List<Subscription> {
        return transaction {
            Subscriptions.select { Subscriptions.userId eq userId }
                .map { it.toSubscription() }
        }
    }

    // Function to get details of a specific subscription
    fun getSubscriptionDetails(userId: Int, subscriptionId: Int): Subscription? {
        return transaction {
            Subscriptions.select { Subscriptions.userId eq userId and (Subscriptions.subId eq subscriptionId) } // Fix the reference to subId
                .singleOrNull()
                ?.toSubscription()
        }
    }

    // Add other subscription-related functions as needed
    private fun ResultRow.toSubscription(): Subscription {
        // Convert ResultRow to Subscription data class
        return Subscription(
            subId = this[Subscriptions.subId],
            userId = this[Subscriptions.userId],
            type = this[Subscriptions.type],
            startDate = this[Subscriptions.startDate],
            endDate = this[Subscriptions.endDate],
            paymentFrequency = this[Subscriptions.paymentFrequency]
        )
    }

}
