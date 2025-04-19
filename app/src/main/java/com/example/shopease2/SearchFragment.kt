package com.example.shopease2

import SearchAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var fruitsRecyclerView: RecyclerView
    private lateinit var soapsDetergentsRecyclerView:RecyclerView
    private lateinit var bakeryRecyclerView:RecyclerView
    private lateinit var cerealsandpulses:RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fruitsRecyclerView = view.findViewById(R.id.fruitsVegetablesRecyclerView)
        soapsDetergentsRecyclerView=view.findViewById(R.id.soapsDetergentsRecyclerView)
        bakeryRecyclerView=view.findViewById(R.id.bakeryRecyclerView)
        cerealsandpulses=view.findViewById(R.id.cerealsandpulses)

        val db = FirebaseFirestore.getInstance()
        db.collection("Products")
            .whereEqualTo("category", "pulses")
            .get()
            .addOnSuccessListener { documents ->
                val cerealList = mutableListOf<Product>()
                for (document in documents) {
                    val name = document.getString("name") ?: ""
                    val price = document.getString("price") ?: ""
                    val imageUrl = document.getString("image") ?: "" // Or map from Firestore if you're storing image keys
                    cerealList.add(Product(name, price, imageUrl))

                }

                cerealsandpulses.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                cerealsandpulses.adapter = SearchAdapter(cerealList)
            }
            .addOnFailureListener { exception ->
                Log.d("SearchFragment", "Error getting fruits: ", exception)
            }
        db.collection("Products")
            .whereEqualTo("category", "fruits")
            .get()
            .addOnSuccessListener { documents ->
                val fruitsList = mutableListOf<Product>()
                for (document in documents) {
                    val name = document.getString("name") ?: ""
                    val price = document.getString("price") ?: ""
                    val imageUrl = document.getString("image") ?: "" // Or map from Firestore if you're storing image keys
                    fruitsList.add(Product(name, price, imageUrl))

                }

                fruitsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                fruitsRecyclerView.adapter = SearchAdapter(fruitsList)
            }
            .addOnFailureListener { exception ->
                Log.d("SearchFragment", "Error getting fruits: ", exception)
            }

        db.collection("Products")
            .whereEqualTo("category", "soaps")
            .get()
            .addOnSuccessListener { documents ->
                val soapsList = mutableListOf<Product>()
                for (document in documents) {
                    val name = document.getString("name") ?: ""
                    val price = document.getString("price") ?: ""
                    val imageUrl = document.getString("image") ?: ""
                    soapsList.add(Product(name, price, imageUrl))
                }

                soapsDetergentsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                soapsDetergentsRecyclerView.adapter = SearchAdapter(soapsList)
            }

        // Repeat for bakery
        db.collection("Products")
            .whereEqualTo("category", "bakery")
            .get()
            .addOnSuccessListener { documents ->
                val bakeryList = mutableListOf<Product>()
                for (document in documents) {
                    val name = document.getString("name") ?: ""
                    val price = document.getString("price") ?: ""
                    val imageUrl = document.getString("image") ?: ""
                    bakeryList.add(Product(name, price, imageUrl))
                }

                bakeryRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                bakeryRecyclerView.adapter = SearchAdapter(bakeryList)
            }



    }


}
