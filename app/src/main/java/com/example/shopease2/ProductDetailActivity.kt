package com.example.shopease2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productdetails)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val productName = intent.getStringExtra("PRODUCT_NAME")
        val productPrice = intent.getStringExtra("PRODUCT_PRICE")
        val productImageUrl = intent.getStringExtra("PRODUCT_IMAGE_URL")
//        val buyNowButton=findViewById<Button>(R.id.buyNowButton)

        val nameTextView = findViewById<TextView>(R.id.productName)
        val priceTextView = findViewById<TextView>(R.id.productPrice)
        val imageView = findViewById<ImageView>(R.id.productImageView)
        val descTextView = findViewById<TextView>(R.id.productDescription)

        nameTextView.text = productName
        priceTextView.text = productPrice

        Glide.with(this)
            .load(productImageUrl)
            .into(imageView)
        db = FirebaseFirestore.getInstance()

        // Fetch description by product name
        db.collection("Products")
            .whereEqualTo("name", productName)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val doc = documents.documents[0]
                    val description = doc.getString("desc") ?: "No description available"
                    descTextView.text = description
                } else {
                    descTextView.text = "Description not found"
                }
            }
            .addOnFailureListener {
                descTextView.text = "Failed to load description"
                Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}



