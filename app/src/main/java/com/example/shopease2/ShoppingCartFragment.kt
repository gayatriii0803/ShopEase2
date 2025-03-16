package com.example.shopease2

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShoppingCartFragment : Fragment(R.layout.fragment_shoppingcart) {

    private lateinit var cartAdapter: CartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartItemsRecyclerView = view.findViewById<RecyclerView>(R.id.cartItemsRecyclerView)
        val cartItemCount = view.findViewById<TextView>(R.id.cartItemCount)
        val subtotalTextView = view.findViewById<TextView>(R.id.subtotalTextView)
        val totalTextView = view.findViewById<TextView>(R.id.totalTextView)

        val cartItems = CartManager.getCartItems()
        cartAdapter = CartAdapter(cartItems)

        cartItemsRecyclerView.adapter = cartAdapter
        cartItemsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Update UI
        cartItemCount.text = "${cartItems.size} items"
        val subtotal = CartManager.getSubtotal()
        val delivery = 2.99
        val total = subtotal + delivery

        subtotalTextView.text = "$%.2f".format(subtotal)
        totalTextView.text = "$%.2f".format(total)

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

