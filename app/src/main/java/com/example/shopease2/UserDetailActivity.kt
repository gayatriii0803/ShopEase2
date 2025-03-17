package com.example.shopease2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore

class UserDetailActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdetail)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        db = FirebaseFirestore.getInstance()

        val productName = intent.getStringExtra("PRODUCT_NAME")
        val productPrice = intent.getStringExtra("PRODUCT_PRICE")
        val productImageUrl = intent.getStringExtra("PRODUCT_IMAGE_URL")

        val fullName = findViewById<EditText>(R.id.etFullName)
        val phone = findViewById<EditText>(R.id.etPhoneNumber)
        val area = findViewById<EditText>(R.id.etArea)
        val city = findViewById<EditText>(R.id.etCity)
        val street = findViewById<EditText>(R.id.etStreetAddress)
        val pincode = findViewById<EditText>(R.id.etPincode)

        val confirmButton = findViewById<MaterialButton>(R.id.btnConfirm)

        confirmButton.setOnClickListener {
            val fullNameText = fullName.text.toString().trim()
            val phoneText = phone.text.toString().trim()
            val areaText = area.text.toString().trim()
            val cityText = city.text.toString().trim()
            val streetText = street.text.toString().trim()
            val pincodeText = pincode.text.toString().trim()

            // ✅ Field validations
            when {
                fullNameText.isEmpty() || phoneText.isEmpty() || areaText.isEmpty() ||
                        cityText.isEmpty() || streetText.isEmpty() || pincodeText.isEmpty() -> {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }

                phoneText.length < 10 -> {
                    Toast.makeText(this, "Enter a valid phone number", Toast.LENGTH_SHORT).show()
                }

                pincodeText.length != 6 -> {
                    Toast.makeText(this, "Pincode must be 6 digits", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    val userDetails = hashMapOf(
                        "fullName" to fullNameText,
                        "phoneNumber" to phoneText,
                        "area" to areaText,
                        "city" to cityText,
                        "streetAddress" to streetText,
                        "pincode" to pincodeText,
                        "productName" to productName,
                        "productPrice" to productPrice,
                        "productImageUrl" to productImageUrl
                    )

                    db.collection("orders")
                        .add(userDetails)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Details saved!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, OrderSummaryActivity::class.java)
                            intent.putExtra("PRODUCT_NAME", productName)
                            intent.putExtra("PRODUCT_PRICE", productPrice)
                            intent.putExtra("PRODUCT_IMAGE_URL", productImageUrl)
                            startActivity(intent)
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Error saving details", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
    }
}
