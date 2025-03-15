package com.example.shopease2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.drawerlayout.widget.DrawerLayout

class HomeFragment : Fragment() {

    private lateinit var drawerLayout: DrawerLayout  // Reference to DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Get DrawerLayout from the Activity
        drawerLayout = requireActivity().findViewById(R.id.drawerLayout)

        // Find btnMenu and set click listener
        val btnMenu = view.findViewById<Button>(R.id.btnMenu)
        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return view
    }
}
