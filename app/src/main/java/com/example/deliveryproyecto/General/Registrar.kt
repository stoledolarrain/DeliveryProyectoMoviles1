package com.example.deliveryproyecto.General

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.deliveryproyecto.DataModels.RegisterResponse
import com.example.deliveryproyecto.Repositories.ApiRepository
import com.example.deliveryproyecto.R
import com.example.deliveryproyecto.databinding.RegistrarBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registrar : Fragment() {

    private var _binding: RegistrarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RegistrarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()


            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                registerUser(name, email, password)
            } else {
                Toast.makeText(context, "Por favor, llena los datos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(name: String, email: String, password: String) {
        val apiService = ApiRepository.getApiService()
        val registerRequest = mapOf(
            "name" to name,
            "email" to email,
            "password" to password,
            "role" to "1"
        )

        apiService.registerUser(registerRequest).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()?.message
                    if (message != null) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                    findNavController().navigate(R.id.action_registrar_to_login)
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(context, "Error en el servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
