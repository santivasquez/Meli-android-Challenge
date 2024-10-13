package com.example.meli_challenge.ui.search

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.meli_challenge.databinding.FragmentSearchBinding
import com.example.meli_challenge.domain.model.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SearchViewModel>()
    private var selectedCategory = ""
    private var categories = emptyList<Category>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intiUi()
        initState()
    }

    private fun intiUi() {
        initSearchButton()

    }

    private fun initState() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categories.collect {
                    categories = it ?: emptyList()
                    initCategoriesSpinner()
                }
            }
        }
    }

    private fun initCategoriesSpinner() {
        val spinnerCategory = binding.spinnerCategory

        val adapter =
            context?.let { ArrayAdapter(it, R.layout.simple_spinner_item, categories.map { it.name }) }
        adapter?.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter
        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCategory = categories[position].id
                viewModel.selectedCategoryPosition = position // Save selected position in ViewModel
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // TODO Handle case when nothing is selected, if needed
            }
        }
        // Restore the spinner's state from the ViewModel
        binding.spinnerCategory.setSelection(viewModel.selectedCategoryPosition)
    }

    private fun initSearchButton() {
        binding.searchBtn.setOnClickListener {
            val query = binding.searchEditTxt.text.toString()
            if (query.isEmpty() && selectedCategory.isEmpty() ) {
                Toast.makeText(context, "Enter some information to search", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToProductsListFragment(
                        query,
                        selectedCategory
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
