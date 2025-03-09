package com.example.shopease2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryAdapter(
    private val items: MutableList<GroceryItem>,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {

    inner class GroceryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.tvItemName)
        val qty = view.findViewById<TextView>(R.id.tvItemQty)
        val price = view.findViewById<TextView>(R.id.tvItemPrice)
        val delete = view.findViewById<ImageButton>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grocery, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.qty.text = "Qty: ${item.quantity}"
        holder.price.text = "₹${item.price}"
        holder.delete.setOnClickListener {
            onDelete(position)
        }
    }

    override fun getItemCount() = items.size
}
