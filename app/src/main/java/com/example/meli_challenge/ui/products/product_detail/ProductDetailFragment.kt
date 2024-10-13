package com.example.meli_challenge.ui.products.product_detail

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
import com.bumptech.glide.Glide
import com.example.meli_challenge.databinding.FragmentProdtuctDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProdtuctDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductDetailViewModel by viewModels()
    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProdtuctDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initUiState()
        viewModel.searchProduct(args.productId)
    }

    private fun initUi() {
        binding.icBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is DetailLoadingState.Loading ->  stateIsLoading()
                        is DetailLoadingState.Error -> TODO()
                        is DetailLoadingState.Success -> stateIsSuccess(it)
                    }
            }
        }
        } }

    private fun stateIsSuccess(state: DetailLoadingState.Success) {
        binding.progressBar.visibility = View.GONE
        binding.scrollView.visibility = View.VISIBLE

        binding.textProductTitle.text = state.product.name
        binding.textProductPrice.text = state.product.price
        context?.let {
            Glide.with(it)
                .load(state.product.thumbnail)
                .dontAnimate()
                .into(binding.imageProduct)
        }
    }

    private fun stateIsLoading() {
            binding.progressBar.visibility = View.VISIBLE
            binding.scrollView.visibility = View.GONE
        }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}