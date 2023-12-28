package com.example.prosubscriberapp.model

//User Data Class
data class User(
    private val userId: Int,
    private val deviceType: String,
    private val name: String,
    private val emailAddress: String,
    private val password: String,
    private val age: Int,
    private val location: String,
    private val gender: String,
    private val gaid: String
) {
    fun getUserID(): Int = userId
    fun getDeviceType(): String = deviceType
    fun getName(): String = name
    fun getEmailAddress(): String = emailAddress
    fun getPassword(): String = password
    fun getAge(): Int = age
    fun getLocation(): String = location
    fun getGender(): String = gender
    fun getGAID(): String = gaid
}