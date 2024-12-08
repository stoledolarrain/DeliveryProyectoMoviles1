package com.example.deliveryproyecto.Api


import com.example.deliveryproyecto.DataModels.LoginResponse
import com.example.deliveryproyecto.DataModels.RegisterResponse
import com.example.deliveryproyecto.DataModels.Restaurant
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JSONApiDelivery {
    @POST("users/login")
    fun loginUser(@Body loginRequest: Map<String, String>): Call<LoginResponse>

    @POST("users")
    fun registerUser(@Body registerRequest: Map<String, String>): Call<RegisterResponse>

    @GET("restaurants")
    fun getRestaurants(): Call<List<Restaurant>>

    @GET("restaurants/{id}")
    fun getRestaurantDetails(@Path("id") id: Int): Call<Restaurant>


}
