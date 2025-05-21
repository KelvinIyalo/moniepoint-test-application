package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.usecase

import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.SearchDeliveries
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.repository.SearchDeliveriesRepository
import javax.inject.Inject

class SearchDeliveriesUseCase @Inject constructor(private val searchDeliveriesRepository: SearchDeliveriesRepository) {
    suspend operator fun invoke(): List<SearchDeliveries>? = searchDeliveriesRepository.searchDeliveries()
}