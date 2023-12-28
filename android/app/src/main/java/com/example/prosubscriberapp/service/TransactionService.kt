package com.example.prosubscriberapp.service

import com.example.prosubscriberapp.config.Payments
import com.example.prosubscriberapp.model.Payment
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class TransactionService {

    // Function to get transaction history for a user
    fun getTransactionHistory(userId: Int): List<Payment> {
        return transaction {
            Payments.select { Payments.userid eq userId }
                .map { it.toPayment() }
        }
    }

    // Add other transaction-related functions as needed
    private fun ResultRow.toPayment(): Payment {
        // Convert ResultRow to Payment data class
        return Payment(
            paymentDate = this[Payments.paymentDate],
            price = this[Payments.price],
            paymentMethod = this[Payments.paymentMethod],
            subId = this[Payments.subid],
            userId = this[Payments.userid]
        )
    }

}
