package com.example.shopease2

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var menuList: RecyclerView
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "User")
        val savedEmail = sharedPreferences.getString("user_email", "email@example.com")

        val nameTextView = view.findViewById<TextView>(R.id.tvUserName)
        val userEmailTextView = view.findViewById<TextView>(R.id.tvUserEmail)
        nameTextView.text = "$userName"
        userEmailTextView.text = "$savedEmail"

        menuList = view.findViewById(R.id.rvMenuList)
        logoutButton = view.findViewById(R.id.btnLogout)

        val menuItems = listOf(
            MenuItem("Orders", R.drawable.ic_order),
            MenuItem("My Details", R.drawable.ic_person),
            MenuItem("Delivery Address", R.drawable.ic_location),
            MenuItem("Payment Methods", R.drawable.ic_payment),
            MenuItem("Notifications", R.drawable.ic_notifications),
            MenuItem("Help", R.drawable.ic_help),
            MenuItem("About", R.drawable.ic_about)
        )

        // Setting up RecyclerView
        menuList.layoutManager = LinearLayoutManager(requireContext())

        menuList.adapter = MenuAdapter(menuItems) { menuItem ->
            when (menuItem.title) {
                "Orders" -> {
                    val intent = Intent(view.context, MyOrderActivity::class.java)
                    startActivity(intent)
                }
                // Add more menu actions here if needed
            }
        }

        // Logout Button Click Listener
        logoutButton.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes") { _, _ ->
                    // Navigate to Login page
                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss() // Close the dialog
                }
                .show()
        }

        return view
    }
}






