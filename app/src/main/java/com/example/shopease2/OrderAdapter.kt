package com.example.shopease2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class OrderAdapter(private val orderList: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.tvOrderProductName)
        val productPrice: TextView = itemView.findViewById(R.id.tvOrderProductPrice)
        val productImage: ImageView = itemView.findViewById(R.id.ivOrderProductImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]
        holder.productName.text = order.productName
        holder.productPrice.text = "Price: ${order.productPrice}"
        Glide.with(holder.itemView.context)
            .load(order.productImageUrl)
            .into(holder.productImage)
    }

    override fun getItemCount(): Int = orderList.size
}
