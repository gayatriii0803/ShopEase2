package com.example.shopease2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShoppingCartFragment : Fragment(R.layout.fragment_shoppingcart) {

    private lateinit var cartAdapter: CartAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartItemsRecyclerView = view.findViewById<RecyclerView>(R.id.cartItemsRecyclerView)
        val cartItemCount = view.findViewById<TextView>(R.id.cartItemCount)
        val continueShoppingButton=view.findViewById<Button>(R.id.continueShoppingButton)


        val cartItems = CartManager.getCartItems()
        cartAdapter = CartAdapter(cartItems)

        cartItemsRecyclerView.adapter = cartAdapter
        cartItemsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Update UI
        cartItemCount.text = "${cartItems.size} items"
        val subtotal = CartManager.getSubtotal()
        val delivery = 2.99
        val total = subtotal + delivery





        // Show empty view if needed
        val emptyView = view.findViewById<LinearLayout>(R.id.emptyCartView)
        if (cartItems.isEmpty()) {
            emptyView.visibility = View.VISIBLE
            cartItemsRecyclerView.visibility = View.GONE
        } else {
            emptyView.visibility = View.GONE
            cartItemsRecyclerView.visibility = View.VISIBLE
        }
    }
}

