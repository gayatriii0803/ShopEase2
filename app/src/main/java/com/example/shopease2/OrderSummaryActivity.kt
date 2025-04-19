package com.example.shopease2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class OrderSummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordersummary)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val productName = intent.getStringExtra("PRODUCT_NAME")
        val productPrice = intent.getStringExtra("PRODUCT_PRICE")
        val productImageUrl = intent.getStringExtra("PRODUCT_IMAGE_URL")

        val nameText = findViewById<TextView>(R.id.tvProductName)
        val priceText = findViewById<TextView>(R.id.tvProductPrice)
        val productImage = findViewById<ImageView>(R.id.ivProductImage)
        val proceedButton = findViewById<Button>(R.id.btnProceed)
        val codRadio = findViewById<RadioButton>(R.id.radioCashOnDelivery)

        nameText.text = productName
        priceText.text = "Price: $productPrice"

        if (!productImageUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(productImageUrl)
                .into(productImage)
        }

        proceedButton.setOnClickListener {
            if (codRadio.isChecked) {
                Toast.makeText(this, "Order placed with Cash on Delivery", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("SHOW_HOME_FRAGMENT", true)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Please select payment method", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
