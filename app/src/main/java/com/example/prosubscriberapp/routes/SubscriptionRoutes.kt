package com.example.prosubscriberapp.routes

import com.example.prosubscriberapp.service.SubscriptionService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.subscriptionRoutes(subscriptionService: SubscriptionService) {
    route("/subscriptions") {

        // View all active subscriptions
        get {
            val userId = call.parameters["userId"]?.toIntOrNull()
            if (userId != null) {
                val activeSubscriptions = subscriptionService.getAllActiveSubscriptions(userId)
                call.respond(activeSubscriptions)
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
            }
        }

        // View a specific subscription
        get("/{subscriptionId}") {
            val userId = call.parameters["userId"]?.toIntOrNull()
            val subscriptionId = call.parameters["subscriptionId"]?.toIntOrNull()

            if (userId != null && subscriptionId != null) {
                val subscription = subscriptionService.getSubscriptionDetails(userId, subscriptionId)
                if (subscription != null) {
                    call.respond(subscription)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Subscription not found")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid user ID or subscription ID")
            }
        }

    }
}