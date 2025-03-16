package com.example.shopease2

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.shopease2.databinding.FragmentDrawerMenuBinding
import com.google.android.material.navigation.NavigationView

class DrawerMenuFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private var _binding: FragmentDrawerMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrawerMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("AppPrefs", 0)
        val isDarkMode = sharedPreferences.getBoolean("DarkMode", false)
        updateTheme(isDarkMode)

        // Set navigation drawer item listener
        binding.navView.setNavigationItemSelectedListener(this)

        // Set user details dynamically
        val headerView = binding.navView.getHeaderView(0)
        val tvUserName = headerView.findViewById<TextView>(R.id.tvUserName)
        val tvUserEmail = headerView.findViewById<TextView>(R.id.tvUserEmail)

        tvUserName.text = "SS"
        tvUserEmail.text = "abc123@example.com"
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> showToast("Home")
            R.id.nav_search -> showToast("Search")
            R.id.nav_notifications -> showToast("Notifications")
            R.id.nav_settings -> showToast("Settings")
            R.id.nav_theme -> toggleTheme()
            R.id.nav_logout -> showToast("Logged Out")
        }

        // Close drawer after selection
        requireActivity().findViewById<DrawerLayout>(R.id.drawerLayout).closeDrawer(GravityCompat.START)
        return true
    }

    private fun toggleTheme() {
        val isDarkMode = sharedPreferences.getBoolean("DarkMode", false)
        val newMode = if (isDarkMode) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
        AppCompatDelegate.setDefaultNightMode(newMode)

        sharedPreferences.edit().putBoolean("DarkMode", !isDarkMode).apply()

        // Recreate activity to apply theme changes
        requireActivity().recreate()
    }

    private fun updateTheme(isDarkMode: Boolean) {
        val mode = if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Prevent memory leaks
    }
}
