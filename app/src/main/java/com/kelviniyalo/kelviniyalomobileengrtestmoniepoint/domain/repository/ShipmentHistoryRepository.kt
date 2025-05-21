package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.repository

import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.ShipmentDetails

interface ShipmentHistoryRepository {

    fun getTransactions(): List<ShipmentDetails>?
}