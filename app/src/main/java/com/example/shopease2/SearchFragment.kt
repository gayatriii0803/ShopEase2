package com.example.shopease2

import SearchAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var fruitsRecyclerView: RecyclerView
    private lateinit var soapsDetergentsRecyclerView:RecyclerView
    private lateinit var bakeryRecyclerView:RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fruitsRecyclerView = view.findViewById(R.id.fruitsVegetablesRecyclerView)
        soapsDetergentsRecyclerView=view.findViewById(R.id.soapsDetergentsRecyclerView)
        bakeryRecyclerView=view.findViewById(R.id.bakeryRecyclerView)

        val products = listOf(
            Product("Apple", "₹60/kg", R.drawable.ic_fruits),
            Product("Banana", "₹40/kg", R.drawable.ic_dairy),
            Product("Tomato", "₹30/kg", R.drawable.ic_vegetables)
        )

        val products1 = listOf(
            Product("Apple", "₹60/kg", R.drawable.ic_fruits),
            Product("Banana", "₹40/kg", R.drawable.ic_dairy),
            Product("Tomato", "₹30/kg", R.drawable.ic_vegetables)
        )

        val products2 = listOf(
            Product("Apple", "₹60/kg", R.drawable.ic_fruits),
            Product("Banana", "₹40/kg", R.drawable.ic_dairy),
            Product("Tomato", "₹30/kg", R.drawable.ic_vegetables)
        )

        fruitsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        fruitsRecyclerView.adapter = SearchAdapter(products)

        soapsDetergentsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        soapsDetergentsRecyclerView.adapter = SearchAdapter(products1)

        bakeryRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bakeryRecyclerView.adapter = SearchAdapter(products2)


    }


}
