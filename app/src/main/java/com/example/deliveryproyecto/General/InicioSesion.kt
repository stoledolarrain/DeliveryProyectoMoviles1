package com.example.deliveryproyecto.General

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.deliveryproyecto.Repositories.ApiRepository
import com.example.deliveryproyecto.DataModels.LoginResponse
import com.example.deliveryproyecto.R
import com.example.deliveryproyecto.databinding.LoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InicioSesion : Fragment() {

    private var _binding: LoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Manejar el clic en el botón de iniciar sesión
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(context, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_registrar)
        }
    }

    private fun loginUser(email: String, password: String) {
            val apiService = ApiRepository.getApiService()
            val loginRequest = mapOf("email" to email, "password" to password)

            apiService.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val token = response.body()?.token
                        if (token != null) {
                            saveToken(token)
                            Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            navigateToRestaurants()
                        } else {
                            Toast.makeText(context, "Error al obtener el token", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(context, "Error en el servidor: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun navigateToRestaurants() {
        val currentDestination = findNavController().currentDestination?.id
        if (currentDestination == R.id.loginFragment) {
            findNavController().navigate(R.id.action_login_to_listaRestaurantes)
        }
    }


    private fun saveToken(token: String) {
        val sharedPreferences = requireContext().getSharedPreferences("delivery_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("auth_token", token).apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
