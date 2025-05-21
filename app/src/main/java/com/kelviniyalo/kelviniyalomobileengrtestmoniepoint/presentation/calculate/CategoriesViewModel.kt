package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.calculate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.usecase.GetShipmentCategoryUseCase
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.ShipmentStatus
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.TFUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val getShipmentCategoryUseCase: GetShipmentCategoryUseCase) :
    ViewModel() {
    private var _transactions: MutableLiveData<TFUiState> = MutableLiveData(TFUiState(ShipmentStatus.ALL, emptyList()))
    val transactions: LiveData<TFUiState> = _transactions
    private val _transactionType = MutableStateFlow(ShipmentStatus.ALL)

    private val _searchQuery = MutableStateFlow("")

    init {
        viewModelScope.launch {
            retrieveCategories()
        }
    }

    private suspend fun retrieveCategories() {
        combine(
            _transactionType,
            flowOf(getShipmentCategoryUseCase()),
            _searchQuery
        ) { transactionType, transactions, searchQuery ->
            TFUiState(
                transactionType,
                categories = transactions
            )
        }.collect { transactions ->
            _transactions.value = transactions

        }
    }


}