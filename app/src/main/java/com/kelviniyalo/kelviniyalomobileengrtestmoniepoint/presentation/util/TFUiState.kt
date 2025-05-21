package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util

import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.Categories
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.SearchDeliveries
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.ShipmentDetails

data class TFUiState(
    val shipmentStatus: ShipmentStatus? = null,
    val shipmentDetails: List<ShipmentDetails>? = null,
    val categories: List<Categories>? = null,
    val searchDeliveries: List<SearchDeliveries>? = null
)
