package com.example.meli_challenge.ui.products.products_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meli_challenge.databinding.FragmentProductsListBinding
import com.example.meli_challenge.domain.model.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsListFragment : Fragment() {

    private var _binding: FragmentProductsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsListViewModel by viewModels()
    private lateinit var productsAdapter: ProductsAdapter
    private val args: ProductsListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        viewModel.searchProducts(args.query, args.category)
    }

    private fun initUi() {
        binding.icBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        initUiState()
        initList()
    }



    private fun initUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is ProductsLoadingState.Loading ->  stateIsLoading()
                        is ProductsLoadingState.Error -> TODO()
                        is ProductsLoadingState.Success -> stateIsSuccess(it)
                    }
                }
            }
        }
    }

    private fun stateIsSuccess(state: ProductsLoadingState.Success) {
        binding.progressBar.visibility = View.GONE
        if (state.products.isEmpty()) {
            binding.productListRv.visibility = View.GONE
            binding.notFoundText.visibility = View.VISIBLE
        } else {
            binding.productListRv.visibility = View.VISIBLE
            productsAdapter.updateList(state.products)
        }
    }

    private fun stateIsLoading() {
        binding.productListRv.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun initList() {
        productsAdapter = ProductsAdapter(onItemSelected = { onProductSelected(it) })
        binding.productListRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productsAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onProductSelected(product: Product) {
        findNavController().navigate(
            ProductsListFragmentDirections.actionProductsListFragmentToProdtuctDetailFragment(
                product.id
            )
        )
    }
}
