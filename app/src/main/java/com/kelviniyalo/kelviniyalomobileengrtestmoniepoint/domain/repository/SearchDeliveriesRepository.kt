package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.repository

import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.SearchDeliveries

interface SearchDeliveriesRepository {

    suspend fun searchDeliveries(): List<SearchDeliveries>?
}