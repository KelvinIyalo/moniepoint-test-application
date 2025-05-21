package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.SearchDeliveries
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.usecase.SearchDeliveriesUseCase
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.TFUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDeliveriesViewModel @Inject constructor(private val searchDeliveriesUseCase: SearchDeliveriesUseCase) :
    ViewModel() {
    private var _transactions: MutableLiveData<TFUiState> =
        MutableLiveData(TFUiState(null, emptyList()))
    val transactions: LiveData<TFUiState> = _transactions

    private val _searchQuery = MutableStateFlow("")

    init {
        viewModelScope.launch {
            searchDeliveries()
        }
    }

    private suspend fun searchDeliveries() {
        combine(
            flowOf(searchDeliveriesUseCase()),
            _searchQuery
        ) { transactions, searchQuery ->

            TFUiState(
                null,
                searchDeliveries = transactions?.filter {
                    searchFilterPredicate(searchQuery, it)
                }
            )

        }.collect { transactions ->
            _transactions.value = transactions

        }
    }

    fun filterBySearchQuery(searchQuery: String) {
        _searchQuery.value = searchQuery
    }

    private fun searchFilterPredicate(searchQuery: String, transaction: SearchDeliveries): Boolean {
        return transaction.status?.contains(searchQuery, true) == true ||
                transaction.itemName.toString().contains(searchQuery, true) ||
                transaction.receipt_number?.contains(searchQuery, true) == true
    }

}