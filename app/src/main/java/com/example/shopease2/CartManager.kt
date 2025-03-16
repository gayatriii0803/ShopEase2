package com.example.shopease2

object CartManager {
    private val cartItems = mutableListOf<Product>()

    fun addToCart(product: Product) {
        val existingItem = cartItems.find { it.name == product.name }
        if (existingItem != null) {
            existingItem.Quantity += 1
        } else {
            cartItems.add(product.copy(Quantity = 1))
        }
    }

    fun getCartItems(): List<Product> = cartItems

    fun getCartItemCount(): Int = cartItems.sumOf { it.Quantity }

    fun getSubtotal(): Double {
        return cartItems.sumOf {
            (it.price.toDoubleOrNull() ?: 0.0) * it.Quantity
        }
    }

    fun clearCart() {
        cartItems.clear()
    }
}
