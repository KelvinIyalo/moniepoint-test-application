package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.usecase

import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.Categories
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.repository.ShipmentCategoryRepository
import javax.inject.Inject

class GetShipmentCategoryUseCase @Inject constructor(private val shipmentCategoryRepository: ShipmentCategoryRepository) {
    suspend operator fun invoke(): List<Categories>? = shipmentCategoryRepository.getCategories()
}