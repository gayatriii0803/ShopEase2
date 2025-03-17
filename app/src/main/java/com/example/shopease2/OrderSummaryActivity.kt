package com.example.shopease2


import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class OrderSummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordersummary)  // Create this layout
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
                // You can navigate to a success screen or back to home
            } else {
                Toast.makeText(this, "Please select payment method", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
