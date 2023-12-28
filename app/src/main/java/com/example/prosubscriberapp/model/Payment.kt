package com.example.prosubscriberapp.model

import java.time.Instant

data class Payment(
    private val paymentDate: Instant,
    private val price: Float,
    private val paymentMethod: String,
    private val subId: Int,
    private val userId: Int
) {
    // Getter functions
    fun getPaymentDate(): Instant = paymentDate
    fun getPrice(): Float = price
    fun getPaymentMethod(): String = paymentMethod
    fun getSubId(): Int = subId
    fun getUserId(): Int = userId
}
