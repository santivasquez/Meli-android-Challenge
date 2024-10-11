package com.example.meli_challenge.ui.products

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.meli_challenge.databinding.ItemProductBinding
import com.example.meli_challenge.domain.model.Product

class ProductsViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(view)

    fun render(product: Product){
        binding.nameTxt.text= product.name
    }
}
