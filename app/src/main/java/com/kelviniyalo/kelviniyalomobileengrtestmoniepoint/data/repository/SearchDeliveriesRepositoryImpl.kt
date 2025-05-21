package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.repository

import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.SearchDeliveries
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.repository.SearchDeliveriesRepository
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.framwork.util.JsonDecoder
import javax.inject.Inject

class SearchDeliveriesRepositoryImpl @Inject constructor(private val jsonDecoder: JsonDecoder) :
    SearchDeliveriesRepository {
    override suspend fun searchDeliveries(): List<SearchDeliveries>? {
        return jsonDecoder.loadJsonFromAsset<List<SearchDeliveries>>("deliveries-mock.json")
    }
}