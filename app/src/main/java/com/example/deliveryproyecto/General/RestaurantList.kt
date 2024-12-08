package com.example.deliveryproyecto.General

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryproyecto.Repositories.ApiRepository
import com.example.deliveryproyecto.Adapters.ProductoAdapter
import com.example.deliveryproyecto.DataModels.Restaurant
import com.example.deliveryproyecto.databinding.ListaProductosBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class RestaurantList : Fragment() {

    private lateinit var binding: ListaProductosBinding
    private lateinit var productoAdapter: ProductoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListaProductosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val restaurantId = arguments?.getInt("restaurant_id") ?: return
        getRestaurantDetails(restaurantId)
    }

    private fun getRestaurantDetails(id: Int) {
        val apiService = ApiRepository.getApiService()
        apiService.getRestaurantDetails(id).enqueue(object : Callback<Restaurant> {
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                if (response.isSuccessful) {
                    val restaurant = response.body()
                    if (restaurant != null) {
                        binding.restaurantName.text = restaurant.name
                        productoAdapter = ProductoAdapter(restaurant.products)
                        binding.productsRecyclerView.adapter = productoAdapter
                        binding.productsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                    }
                } else {
                    Toast.makeText(context, "Error al obtener detalles", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}