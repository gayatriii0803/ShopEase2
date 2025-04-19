package com.example.shopease2

import SuggestedAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var drawerLayout: DrawerLayout  // Reference to DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Get DrawerLayout from the Activity
        drawerLayout = requireActivity().findViewById(R.id.drawerLayout)
        val shopNow=view.findViewById<Button>(R.id.shopNow)
        shopNow.setOnClickListener{
            val intent = Intent(requireContext(), ProductActivity::class.java)
            startActivity(intent)
        }




        // Find btnMenu and set click listener
        val btnMenu = view.findViewById<Button>(R.id.btnMenu)
        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvCategories = view.findViewById<RecyclerView>(R.id.rvCategories)
        val rvSuggested = view.findViewById<RecyclerView>(R.id.rvSuggested)

        val categoryList = listOf(
            category("Fruits and vegetables", R.drawable.ic_fruitsandvegetables),
            category("Soda and detergents", R.drawable.ic_shampoo),
            category("Bakery", R.drawable.ic_bakery),
            category("Dairy", R.drawable.ic_dairy)
        )

        rvCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = CategoryAdapter(categoryList) { selectedCategory ->
            val intent = android.content.Intent(requireContext(), ProductActivity::class.java)
            intent.putExtra("category", selectedCategory.name)
            startActivity(intent)
        }

        val db = FirebaseFirestore.getInstance()
        db.collection("Products")
            .get()
            .addOnSuccessListener { documents ->
                val fruitsList = mutableListOf<Product>()
                for (document in documents) {
                    val name = document.getString("name") ?: ""
                    val price = document.getString("price") ?: ""
                    val imageUrl = document.getString("image") ?: ""
                    fruitsList.add(Product(name, price, imageUrl))
                }

                rvSuggested.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                rvSuggested.adapter = SuggestedAdapter(fruitsList)
            }
            .addOnFailureListener { exception ->
                Log.d("SearchFragment", "Error getting fruits: ", exception)
            }

        // 👇 Update nav header with saved username and email
        val navView = requireActivity().findViewById<com.google.android.material.navigation.NavigationView>(R.id.navView)
        val headerView = navView.getHeaderView(0)

        val userNameTextView = headerView.findViewById<android.widget.TextView>(R.id.userNameheader)
        val userEmailTextView = headerView.findViewById<android.widget.TextView>(R.id.userEmailheader)

        val sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", android.content.Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("user_name", "User")
        val savedEmail = sharedPreferences.getString("user_email", "email@example.com")

        userNameTextView.text = savedUsername
        userEmailTextView.text = savedEmail
    }

}

