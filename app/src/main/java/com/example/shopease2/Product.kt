package com.example.shopease2

import androidx.core.location.LocationRequestCompat.Quality

data class Product(
    val name: String,
    val price: String,
    val imageUrl: String,
    var Quantity: Int = 1
)
