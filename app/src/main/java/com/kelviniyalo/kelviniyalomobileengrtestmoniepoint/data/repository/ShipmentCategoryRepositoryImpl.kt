package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.repository

import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.Categories
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.repository.ShipmentCategoryRepository
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.framwork.util.JsonDecoder
import javax.inject.Inject

class ShipmentCategoryRepositoryImpl @Inject constructor(private val jsonDecoder: JsonDecoder) :
    ShipmentCategoryRepository {
    override fun getCategories(): List<Categories>? {
        return jsonDecoder.loadJsonFromAsset<List<Categories>>("categories-mock.json")
    }
}