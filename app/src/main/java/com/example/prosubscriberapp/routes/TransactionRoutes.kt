package com.example.prosubscriberapp.routes

import com.example.prosubscriberapp.service.TransactionService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.transactionRoutes(transactionService: TransactionService) {
    route("/transactions") {

        // View transaction history
        get("/{userId}") {
            val userId = call.parameters["userId"]?.toIntOrNull()

            if (userId != null) {
                val transactionHistory = transactionService.getTransactionHistory(userId)
                if (transactionHistory.isNotEmpty()) {
                    call.respond(transactionHistory)
                } else {
                    call.respond(HttpStatusCode.NotFound, "No transaction history found for the user")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
            }
        }

    }
}