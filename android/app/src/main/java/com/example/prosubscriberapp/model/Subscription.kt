package com.example.prosubscriberapp.model

import java.time.Instant

data class Subscription(
    private val subId: Int,
    private val userId: Int,
    private val type: String?,
    private val startDate: Instant?,
    private val endDate: Instant?,
    private val paymentFrequency: Int,
)

{
    fun getSubID(): Int = subId
    fun getType(): String? = type
    fun getStartDate(): Instant? = startDate
    fun getEndDate(): Instant? = endDate
    fun getPaymentFrequency(): Int = paymentFrequency
    fun getUserID(): Int = userId
}