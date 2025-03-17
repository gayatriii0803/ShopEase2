import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopease2.CartManager
import com.example.shopease2.Product
import com.example.shopease2.ProductDetailActivity
import com.example.shopease2.R




class SearchAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<SearchAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImageSearch)
        val productName: TextView = itemView.findViewById(R.id.productNameSearch)
        val productPrice: TextView = itemView.findViewById(R.id.productPriceSearch)
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

        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.productImage)


        holder.addToCartButton.setOnClickListener {
            CartManager.addToCart(product)
            Toast.makeText(holder.itemView.context, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("PRODUCT_NAME", product.name)
            intent.putExtra("PRODUCT_PRICE", product.price)
            intent.putExtra("PRODUCT_IMAGE_URL", product.imageUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = productList.size
}

