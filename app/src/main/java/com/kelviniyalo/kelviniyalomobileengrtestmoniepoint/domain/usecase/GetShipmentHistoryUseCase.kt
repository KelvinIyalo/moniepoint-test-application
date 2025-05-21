package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.usecase

import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.ShipmentDetails
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.repository.ShipmentHistoryRepository
import javax.inject.Inject

class GetShipmentHistoryUseCase @Inject constructor(private val shipmentHistoryRepository: ShipmentHistoryRepository) {
    suspend operator fun invoke(): List<ShipmentDetails>? = shipmentHistoryRepository.getTransactions()
}