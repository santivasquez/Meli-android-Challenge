package com.example.meli_challenge.ui.products.products_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meli_challenge.databinding.ItemProductBinding
import com.example.meli_challenge.domain.model.Product

class ProductsViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(view)

    fun render(product: Product, onItemSelected: (Product) -> Unit){
        binding.textTitle.text= product.name
        binding.textPrice.text= "$${product.price}"
        Glide.with(itemView.context)
            .load(product.thumbnail)
            .dontAnimate()
            .into(binding.imageThumbnail)

        binding.productItemParent.setOnClickListener { onItemSelected(product) }
    }
}
