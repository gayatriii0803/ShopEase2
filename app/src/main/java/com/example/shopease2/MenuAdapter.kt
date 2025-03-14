package com.example.shopease2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Data class for menu items
data class MenuItem(val title: String, val iconResId: Int)

class MenuAdapter(private val items: List<MenuItem>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.ivMenuIcon)
        val title: TextView = view.findViewById(R.id.tvMenuTitle)
        val forwardIcon: ImageView = view.findViewById(R.id.ivForwardIcon) // Forward Icon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.icon.setImageResource(item.iconResId)
        holder.forwardIcon.setImageResource(R.drawable.ic_chevron_right) // Set forward icon
    }

    override fun getItemCount() = items.size
}
