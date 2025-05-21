package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.R
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.SearchDeliveries
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.databinding.FragmentSearchBinding
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.Utility.manageElementTransition
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.Utility.startMoveUpAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchDeliveriesViewModel by activityViewModels()
    private lateinit var searchTransactionAdapter: SearchTransactionAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        manageElementTransition()

        initTransactionsRecyclerViewAdapter()
        with(binding) {
            searchEt.requestFocus()
            searchEt.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    val inputMethodManager =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
                }
            }

            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            searchEt.addTextChangedListener {
                viewModel.filterBySearchQuery(it.toString())
            }
        }


        viewModel.transactions.observe(viewLifecycleOwner) { uiState ->
            searchTransactionAdapter.submitList(uiState.searchDeliveries)
            binding.cardviewRecycler.startMoveUpAnimation(requireContext())
        }
    }

    private fun initTransactionsRecyclerViewAdapter() {
        searchTransactionAdapter = SearchTransactionAdapter(
            onItemClicked = { position: Int, itemAtPosition: SearchDeliveries ->

            }
        )
        binding.searchListRv.adapter = searchTransactionAdapter
    }

}