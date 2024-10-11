package com.example.meli_challenge.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meli_challenge.R
import com.example.meli_challenge.domain.model.Product

class ProductsAdapter(private var productsList: List<Product> = emptyList()) :
    RecyclerView.Adapter<ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    fun updateList(list: List<Product>) {
        productsList = list
    }

    override fun getItemCount() = productsList.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.render(productsList[position])
    }

}
