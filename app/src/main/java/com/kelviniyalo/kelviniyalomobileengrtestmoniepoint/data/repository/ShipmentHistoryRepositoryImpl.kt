package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.repository

import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.ShipmentDetails
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.repository.ShipmentHistoryRepository
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.framwork.util.JsonDecoder
import javax.inject.Inject

class ShipmentHistoryRepositoryImpl @Inject constructor(private val jsonDecoder: JsonDecoder) :
    ShipmentHistoryRepository {

    override fun getTransactions(): List<ShipmentDetails>? {
        return jsonDecoder.loadJsonFromAsset<List<ShipmentDetails>>("shipment-mock.json")
    }
}