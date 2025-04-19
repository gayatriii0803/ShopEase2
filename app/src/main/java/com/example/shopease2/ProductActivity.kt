package com.example.shopease2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ProductActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide() // If you're using the old ActionBar
        supportActionBar?.hide()
        val cartIcon = findViewById<ImageView>(R.id.cartIcon)
        cartIcon.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.productRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        fetchProducts()
    }

    private fun fetchProducts() {
        db.collection("Products")
            .get()
            .addOnSuccessListener { documents ->
                val productList = mutableListOf<Product>()
                for (document in documents) {
                    val name = document.getString("name") ?: ""
                    val price = document.getString("price") ?: ""
                    val imageUrl = document.getString("image") ?: ""
                    productList.add(Product(name, price, imageUrl, 1))
                }

                adapter = ProductAdapter(productList)
                recyclerView.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.e("ProductActivity", "Error fetching products", exception)
            }
    }
}
