package com.example.deliveryproyecto.DataModels

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val email: String,
    val password: String
)
data class LoginResponse(
    @SerializedName("access_token") val token: String
)
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val restaurant_id: Int,
    val image: String
)
data class RegisterRequest(
    val email: String,
    val password: String
)
data class RegisterResponse(
    val message: String
)
data class Restaurant(
    val id: Int,
    val name: String,
    val address: String,
    val rating: Float,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("logo") val imageUrl: String,
    val products: List<Product>
)