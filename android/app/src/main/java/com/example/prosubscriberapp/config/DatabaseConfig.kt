package com.example.prosubscriberapp.config

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.transactions.transaction

// Exposed Table
object Users : Table() {
    val userId = integer("userid").autoIncrement()
    val email = varchar("email_address", 255).uniqueIndex()
    val password = varchar("password", 255)
    val age = integer("age")
    val name = varchar("name", 255)
    val location = varchar("location", 255)
    val gender = varchar("gender", 255)
    val deviceType = varchar("device_type", 255)
    val gaid = varchar("gaid", 255)

    override val primaryKey = PrimaryKey(userId)

}
object Subscriptions : Table() {
    val subId = integer("subid").autoIncrement()
    val userId = reference("userid", Users.userId) // Define userId as a reference to Users table
    val type = varchar("type", 255).nullable()
    val startDate = timestamp("start_date").nullable()
    val endDate = timestamp("end_date").nullable()
    val paymentFrequency = integer("payment_frequency")

    override val primaryKey = PrimaryKey(subId,userId)

}

object Payments : Table() {
    val paymentDate = timestamp("payment_date")
    val price = float("price")
    val paymentMethod = varchar("payment_method", 20)
    val subid = integer("subid").references(Subscriptions.subId)
    val userid = integer("userid").references(Users.userId)
}

class DatabaseConfig {
    init {
        Database.connect(
            url = "jdbc:mysql://<YOUR_MYSQL_HOST>:<YOUR_MYSQL_PORT>/practice",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "<YOUR_MYSQL_USERNAME>",
            password = "<YOUR_MYSQL_PASSWORD>"
        )

        transaction {
            // Auto-generate missing tables based on Exposed entities
            SchemaUtils.create(Users, Subscriptions)
        }
    }
}