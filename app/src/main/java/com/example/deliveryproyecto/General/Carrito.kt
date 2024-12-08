package com.example.deliveryproyecto.General

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryproyecto.Adapters.ProductoAdapter
import com.example.deliveryproyecto.Repositories.DatosCarrito
import com.example.deliveryproyecto.databinding.CarritoBinding

class Carrito : Fragment() {

    private var _binding: CarritoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CarritoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView con los productos del carrito
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(context)
        val cartItems = DatosCarrito.getCartItems()
        val adapter = ProductoAdapter(cartItems)
        binding.cartRecyclerView.adapter = adapter

        // Botón para vaciar el carrito
        binding.clearCartButton.setOnClickListener {
            DatosCarrito.clearCart()
            adapter.notifyDataSetChanged()
            Toast.makeText(context, "Carrito vaciado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
