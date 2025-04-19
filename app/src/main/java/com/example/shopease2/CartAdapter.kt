package com.example.shopease2


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CartAdapter(
    private val cartItems: List<Product>,
    private val onItemLongClick: (Product, Int) -> Unit,
    private val onItemClick: (Product, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val selectedItems = mutableSetOf<Product>()
    private var selectionMode = false

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val productQuantity: TextView = itemView.findViewById(R.id.productQuantity)
        val checkBox: CheckBox = itemView.findViewById(R.id.productCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartItems[position]

        holder.productName.text = product.name
        holder.productPrice.text = product.price
        holder.productQuantity.text = "x${product.Quantity}"

        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.productImage)

        // Show checkbox only in selection mode
        holder.checkBox.visibility = if (selectionMode) View.VISIBLE else View.GONE

        // Prevent flicker by removing listener before setting checked state
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = selectedItems.contains(product)

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedItems.add(product)
            } else {
                selectedItems.remove(product)
            }
        }

        holder.itemView.setOnLongClickListener {
            if (!selectionMode) {
                enableSelectionMode()
                selectedItems.add(product)
                notifyItemChanged(position)
                onItemLongClick(product, position)
            }
            true
        }

        holder.itemView.setOnClickListener {
            if (selectionMode) {
                if (selectedItems.contains(product)) {
                    selectedItems.remove(product)
                } else {
                    selectedItems.add(product)
                }
                notifyItemChanged(position)
            }
            onItemClick(product, position)
        }
    }

    override fun getItemCount(): Int = cartItems.size

    fun enableSelectionMode() {
        selectionMode = true
        notifyDataSetChanged()
    }

    fun disableSelectionMode() {
        selectionMode = false
        selectedItems.clear()
        notifyDataSetChanged()
    }

    fun isSelectionMode(): Boolean = selectionMode

    fun getSelectedItems(): List<Product> = selectedItems.toList()

    fun selectAll() {
        selectedItems.clear()
        selectedItems.addAll(cartItems)
        notifyDataSetChanged()
    }

    fun clearSelection() {
        selectedItems.clear()
        notifyDataSetChanged()
    }
}
