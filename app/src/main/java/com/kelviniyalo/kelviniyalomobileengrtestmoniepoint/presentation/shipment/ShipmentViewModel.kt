package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.shipment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.usecase.GetShipmentHistoryUseCase
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.ShipmentStatus
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.TFUiState
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShipmentViewModel @Inject constructor(private val getShipmentHistoryUseCase: GetShipmentHistoryUseCase) :
    ViewModel() {
    private var _transactions: MutableLiveData<TFUiState> = MutableLiveData(TFUiState(ShipmentStatus.ALL, emptyList()))
    val transactions: LiveData<TFUiState> = _transactions
    private val _transactionType = MutableStateFlow(ShipmentStatus.ALL)

    init {
        viewModelScope.launch {
            retrieveTransactions()
        }
    }

    private suspend fun retrieveTransactions() {
        combine(
            _transactionType,
            flowOf(getShipmentHistoryUseCase())
        ) { transactionType, transactions ->

            val filteredTransactions = transactions?.filter {
                when (transactionType) {
                    ShipmentStatus.ALL -> true
                    ShipmentStatus.COMPLETED -> it.status == "Completed"
                    ShipmentStatus.PENDING_ORDER -> it.status == "Pending"
                    ShipmentStatus.CANCELLED -> it.status == "Cancelled"
                    ShipmentStatus.IN_PROGRESS -> it.status == "In-Progress"
                }
            }
            TFUiState(transactionType, filteredTransactions)
        }.collect { transactions ->
            _transactions.value = transactions

        }
    }


     fun retrieveAllShippingCount() = liveData {
         emit(UiState.Loading)
        val result = getShipmentHistoryUseCase()
         emit(UiState.Success(result))
    }


    fun filterByStatus(status: ShipmentStatus) {
        _transactionType.value = status
    }

}