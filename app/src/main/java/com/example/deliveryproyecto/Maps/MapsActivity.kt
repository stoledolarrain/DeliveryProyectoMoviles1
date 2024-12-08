package com.example.deliveryproyecto.Maps

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.deliveryproyecto.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.deliveryproyecto.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Obtener latitud y longitud del Intent
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)

        // Verificar que las coordenadas sean válidas
        if (latitude == 0.0 && longitude == 0.0) {
            Toast.makeText(this, "Ubicación no disponible", Toast.LENGTH_SHORT).show()
            return
        }

        val restaurantLocation = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(restaurantLocation).title("Ubicación del Restaurante"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurantLocation, 15f))
    }
}
