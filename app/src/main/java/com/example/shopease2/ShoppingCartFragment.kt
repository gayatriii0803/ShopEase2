package com.example.shopease2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShoppingCartFragment : Fragment(R.layout.fragment_shoppingcart) {

    private lateinit var cartAdapter: CartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartItemsRecyclerView = view.findViewById<RecyclerView>(R.id.cartItemsRecyclerView)
        val cartItemCount = view.findViewById<TextView>(R.id.cartItemCount)
        val continueShoppingButton = view.findViewById<Button>(R.id.continueShoppingButton)
        val selectAllButton = view.findViewById<Button>(R.id.selectAllButton)
        val deleteSelectedButton = view.findViewById<Button>(R.id.deleteSelectedButton)
        val orderNowButton = view.findViewById<Button>(R.id.orderNowButton)

        val cartItems = CartManager.getCartItems()

        cartAdapter = CartAdapter(
            cartItems,
            onItemLongClick = { _, _ ->
                cartAdapter.enableSelectionMode()
                updateActionModeUI(true)
            },
            onItemClick = { _, _ ->
                updateActionModeUI(
                    cartAdapter.isSelectionMode() && cartAdapter.getSelectedItems().isNotEmpty()
                )
            }
        )

        cartItemsRecyclerView.adapter = cartAdapter
        cartItemsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        cartItemCount.text = "${cartItems.size} items"

        val emptyView = view.findViewById<LinearLayout>(R.id.emptyCartView)
        if (cartItems.isEmpty()) {
            emptyView.visibility = View.VISIBLE
            cartItemsRecyclerView.visibility = View.GONE
        } else {
            emptyView.visibility = View.GONE
            cartItemsRecyclerView.visibility = View.VISIBLE
        }

        continueShoppingButton.setOnClickListener {
            val intent = Intent(requireContext(), ProductActivity::class.java)
            startActivity(intent)
        }

        selectAllButton.setOnClickListener {
            cartAdapter.selectAll()
            updateActionModeUI(true)
        }

        deleteSelectedButton.setOnClickListener {
            cartAdapter.clearSelection()
            updateActionModeUI(false)
        }

        orderNowButton.setOnClickListener {
            val selectedItems = cartAdapter.getSelectedItems()
            if (selectedItems.isNotEmpty()) {
                val intent = Intent(requireContext(), UserDetailActivity::class.java)
                intent.putExtra("PRODUCT_NAME", cartItems[0].name)
                intent.putExtra("PRODUCT_PRICE", cartItems[0].price)
                intent.putExtra("PRODUCT_IMAGE_URL", cartItems[0].imageUrl)
                startActivity(intent)
                cartAdapter.disableSelectionMode()
                updateActionModeUI(false)
            } else {
                Toast.makeText(requireContext(), "No items selected", Toast.LENGTH_SHORT).show()
            }
        }

        // 🛑 Handle back button: exit selection mode first
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (cartAdapter.isSelectionMode()) {
                        cartAdapter.disableSelectionMode()
                        updateActionModeUI(false)
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            })
    }

    private fun updateActionModeUI(show: Boolean) {
        val selectionActionsLayout = view?.findViewById<LinearLayout>(R.id.selectionActionsLayout)
        if (show && cartAdapter.getSelectedItems().isNotEmpty()) {
            selectionActionsLayout?.visibility = View.VISIBLE
        } else {
            selectionActionsLayout?.visibility = View.GONE
        }
    }
}
