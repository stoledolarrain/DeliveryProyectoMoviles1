package com.example.deliveryproyecto.Repositories

import com.example.deliveryproyecto.DataModels.Product

object DatosCarrito {
    private val cartItems = mutableListOf<Product>()

    fun addProduct(product: Product) {
        cartItems.add(product)
    }
    fun getCartItems(): List<Product> {
        return cartItems
    }

    fun clearCart() {
        cartItems.clear()
    }
}
