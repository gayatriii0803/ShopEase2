package com.example.shopease2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ProductDetailActivity : AppCompatActivity() {
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
        val buyNowButton=findViewById<Button>(R.id.buyNowButton)

        val nameTextView = findViewById<TextView>(R.id.productName)
        val priceTextView = findViewById<TextView>(R.id.productPrice)
        val imageView = findViewById<ImageView>(R.id.productImageView)

        nameTextView.text = productName
        priceTextView.text = productPrice

        Glide.with(this)
            .load(productImageUrl)
            .into(imageView)

        buyNowButton.setOnClickListener {
            val intent = Intent(this, UserDetailActivity::class.java)

            // Optionally pass data to UserDetailActivity
            intent.putExtra("PRODUCT_NAME", productName)
            intent.putExtra("PRODUCT_PRICE", productPrice)
            intent.putExtra("PRODUCT_IMAGE_URL",productImageUrl )
            startActivity(intent)
        }
    }
}