package com.example.deliveryproyecto.General

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryproyecto.Repositories.ApiRepository
import com.example.deliveryproyecto.Adapters.RestauranteAdapter
import com.example.deliveryproyecto.DataModels.Restaurant
import com.example.deliveryproyecto.databinding.ListaRestaurantesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantList : Fragment() {

    private var _binding: ListaRestaurantesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListaRestaurantesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.restaurantsRecyclerView.layoutManager = LinearLayoutManager(context)

        val apiService = ApiRepository.getApiService()
        apiService.getRestaurants().enqueue(object : Callback<List<Restaurant>> {
            override fun onResponse(
                call: Call<List<Restaurant>>,
                response: Response<List<Restaurant>>
            ) {
                if (response.isSuccessful) {
                    val restaurants = response.body() ?: emptyList()
                    binding.restaurantsRecyclerView.adapter = RestauranteAdapter(restaurants)
                }
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}