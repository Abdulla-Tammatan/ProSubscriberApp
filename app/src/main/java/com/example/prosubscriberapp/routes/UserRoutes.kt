package com.example.prosubscriberapp.routes

import com.example.prosubscriberapp.service.UserService
import com.example.prosubscriberapp.model.User
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route


fun Route.userRoutes(userService: UserService) {
    route("/users") {
        post("/create") {
            val user = call.receive<User>()
            try {
                val createdUser = userService.createUser(user)
                call.respond(HttpStatusCode.Created, createdUser)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.localizedMessage)
            }
        }

        post("/login") {
            val loginRequest = call.receive<LoginRequest>()
            val user = userService.loginUserByEmail(loginRequest.email, loginRequest.password)
            if (user != null) {
                call.respond(HttpStatusCode.OK, user)
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
            }
        }

        delete("/delete") {
            val deleteRequest = call.receive<DeleteRequest>()
            val deletedUser = userService.deleteUserByEmail(deleteRequest.email, deleteRequest.password)
            if (deletedUser != null) {
                call.respond(HttpStatusCode.OK, "Account deleted successfully")
            } else {
                call.respond(HttpStatusCode.NotFound, "Invalid credentials or user not found")
            }
        }

        put("/update") {
            val updatedUser = call.receive<User>()
            val success = userService.updateUser(updatedUser)
            if (success) {
                call.respond(HttpStatusCode.OK, "User updated successfully")
            } else {
                call.respond(HttpStatusCode.NotFound, "User not found or update failed")
            }
        }
    }
}

data class LoginRequest(val email: String, val password: String)
data class DeleteRequest(val email: String, val password: String)
