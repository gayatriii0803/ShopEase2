package com.example.shopease2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MyOrderActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var orderAdapter: OrderAdapter
    private val orderList = ArrayList<Order>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myorders)

        recyclerView = findViewById(R.id.ordersRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        orderAdapter = OrderAdapter(orderList)
        recyclerView.adapter = orderAdapter

        val db = FirebaseFirestore.getInstance()

//        db.collection("orders")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    val order = Order(
//                        productName = document.getString("productName") ?: "",
//                        productPrice = document.getString("productPrice") ?: "",
//                        productImageUrl = document.getString("productImageUrl") ?: ""
//                    )
//                    orderList.add(order)
//                }
//                orderAdapter.notifyDataSetChanged()
//            }
//            .addOnFailureListener {
//                // Handle errors here
//            }
    }
}
