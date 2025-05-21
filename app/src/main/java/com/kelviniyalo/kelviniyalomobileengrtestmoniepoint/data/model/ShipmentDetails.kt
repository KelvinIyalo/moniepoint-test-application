package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model


data class ShipmentDetails(
    val amount: Int? = null,
    val statusCode: Int? = null,
    val status: String? = null,
    val deliveryDate: String? = null,
    val receipt_number: String? = null,
    val isHeader: Boolean = false,
    val header: String = "",
)
