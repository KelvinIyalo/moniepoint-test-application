package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.repository

import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.Categories

interface ShipmentCategoryRepository {

    fun getCategories(): List<Categories>?
}