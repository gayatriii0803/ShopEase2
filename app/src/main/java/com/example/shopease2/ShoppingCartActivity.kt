package com.example.shopease2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ShoppingCartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ShoppingCartFragment())
            .commit()
    }
}
