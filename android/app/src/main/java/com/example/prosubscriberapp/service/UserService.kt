package com.example.prosubscriberapp.service

import com.example.prosubscriberapp.config.Users
import com.example.prosubscriberapp.model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UserService {
    // Create Account
    fun createUser(user: User) {
        validateUserInput(user)

        return transaction {
            val userId = Users.insertAndGetId {
                it[email] = user.getEmailAddress()
                it[password] = user.getPassword()
                // Add other user properties as needed
            }.value

            //Update the user with additional details
            updateUserDetails(userId, user)
        }
    }

    // Login to Account using email
    fun loginUserByEmail(email: String, password: String): User? {
        return transaction {
            Users.select { Users.email eq email and (Users.password eq password) }
                .singleOrNull()
                ?.toUser()
        }
    }

    // Delete account using email
    fun deleteUserByEmail(email: String, password: String): User? {
        val user = loginUserByEmail(email, password)
        if (user != null) {
            transaction {
                Users.deleteWhere { Users.userId eq user.getUserID() }
            }
        }
        return user
    }

    // Update profile
    fun updateUser(updatedUser: User): Boolean {
        validateUserInput(updatedUser)

        return transaction {
            val result = Users.update({ Users.userId eq updatedUser.getUserID() }) { // Change "id" to "userId" {
                it[email] = updatedUser.getEmailAddress()
                it[password] = updatedUser.getPassword()
                // Add other fields as needed
            }
            result > 0
        }
    }

    private fun updateUserDetails(userId: Int, user: User) {
        validateUserDetails(user)

        transaction {
            Users.update({ Users.userId eq userId }) {
                it[Users.age] = user.getAge()
                it[Users.name] = user.getName()
                it[Users.location] = user.getLocation()
                it[Users.deviceType] = user.getDeviceType()
                it[Users.gaid] = user.getGAID()
            }
        }
    }

    private fun validateUserInput(user: User) {
        if (user.getEmailAddress().isBlank() || user.getPassword().isBlank()) {
            throw IllegalArgumentException("Email and password cannot be blank.")
        }
        // Validate email address
        if (user.getEmailAddress().isBlank()) {
            throw IllegalArgumentException("Email address cannot be blank")
        }
        if (!isValidEmail(user.getEmailAddress())) {
            throw IllegalArgumentException("Invalid email address")
        }

        // Validate password (stronger rules)
        val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{8,}$"
        if (!user.getPassword().matches(passwordRegex.toRegex())) {
            throw IllegalArgumentException("Password must be at least 8 characters long and contain at least one digit, one lowercase and one uppercase letter, and one special character")
        }
    }

    private fun isValidEmail(emailAddress: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return emailAddress.matches(emailRegex.toRegex())
    }

    private fun validateUserDetails(user: User) {
        user.getAge().let {
            if (it < 0) {
                throw IllegalArgumentException("Age must be a non-negative value.")
            }
        }
    }

    // Create Gmail Account
    fun createGmailUser(user: User) {
        validateUserInput(user)

        return transaction {
            val userId = Users.insertAndGetId {
                it[email] = user.getEmailAddress()
                it[password] = user.getPassword()
                // Add other user properties as needed
            }.value

            // Update the user with additional details
            updateUserDetails(userId, user)
        }
    }

    private fun ResultRow.toUser(): User {
        return User(
            userId = this[Users.userId],
            deviceType = this[Users.deviceType],
            emailAddress = this[Users.email],
            password = this[Users.password],
            age = this[Users.age],
            name = this[Users.name],
            location = this[Users.location],
            gender = this[Users.gender],
            gaid = this[Users.gaid]
        )
    }
    // Check if it's a Gmail user
    fun isGmailUser(email: String): Boolean {
        return email.endsWith("@gmail.com")
}

}

