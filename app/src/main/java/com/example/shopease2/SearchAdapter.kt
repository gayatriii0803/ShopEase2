import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shopease2.CartManager
import com.example.shopease2.Product
import com.example.shopease2.R


class SearchAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<SearchAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.name
        holder.productPrice.text = product.price
        holder.productImage.setImageResource(product.imageResId)

        holder.addToCartButton.setOnClickListener {
            CartManager.addToCart(product)
            Toast.makeText(holder.itemView.context, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = productList.size
}

