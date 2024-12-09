package com.example.deliveryproyecto.Adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryproyecto.DataModels.Restaurant
import com.example.deliveryproyecto.R
import com.example.deliveryproyecto.databinding.ItemRestaurantesBinding
import com.squareup.picasso.Picasso

class RestauranteAdapter(private val restaurants: List<Restaurant>) :
    RecyclerView.Adapter<RestauranteAdapter.RestaurantViewHolder>() {

    class RestaurantViewHolder(private val binding: ItemRestaurantesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.restaurantName.text = restaurant.name
            binding.restaurantAddress.text = restaurant.address
            Picasso.get().load(restaurant.imageUrl).into(binding.restaurantImage)

            binding.root.setOnClickListener {
                val bundle = Bundle().apply {
                    putInt("restaurant_id", restaurant.id)
                }
                it.findNavController().navigate(R.id.pantallaListaProductos, bundle)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = ItemRestaurantesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurants[position])
    }

    override fun getItemCount(): Int = restaurants.size
}
