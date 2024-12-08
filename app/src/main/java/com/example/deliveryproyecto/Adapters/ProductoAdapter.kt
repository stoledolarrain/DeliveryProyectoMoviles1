package com.example.deliveryproyecto.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryproyecto.DataModels.Product
import com.example.deliveryproyecto.Repositories.DatosCarrito
import com.example.deliveryproyecto.databinding.ItemProductosBinding
import com.squareup.picasso.Picasso

class ProductoAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductoAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.productName.text = product.name
            binding.productDescription.text = product.description
            binding.productPrice.text = "$${product.price}"
            Picasso.get().load(product.image).into(binding.productImage)

            binding.addToCartButton.setOnClickListener {
                DatosCarrito.addProduct(product)
                Toast.makeText(binding.root.context, "${product.name} agregado al carrito", Toast.LENGTH_SHORT).show()
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size
}
