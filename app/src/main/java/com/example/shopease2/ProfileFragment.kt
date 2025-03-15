package com.example.shopease2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProfileFragment : Fragment() {

    private lateinit var menuList: RecyclerView
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        menuList = view.findViewById(R.id.rvMenuList)
        logoutButton = view.findViewById(R.id.btnLogout)

        val menuItems = listOf(
            MenuItem("Orders", R.drawable.ic_orders),
            MenuItem("My Details", R.drawable.ic_person),
            MenuItem("Delivery Address", R.drawable.ic_location),
            MenuItem("Payment Methods", R.drawable.ic_payment),
            MenuItem("Notifications", R.drawable.ic_notifications),
            MenuItem("Help", R.drawable.ic_help),
            MenuItem("About", R.drawable.ic_about)
        )

        // Setting up RecyclerView
        menuList.layoutManager = LinearLayoutManager(requireContext())
        menuList.adapter = MenuAdapter(menuItems)

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
