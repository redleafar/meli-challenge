package co.meli.domain.models

import com.google.gson.annotations.SerializedName

data class Seller(
    @SerializedName("id") val id: String?,
    @SerializedName("power_seller_status") val powerSellerStatus: String?,
    @SerializedName("car_dealer") val cardDealer: Boolean?,
    @SerializedName("real_state_agency") val realStateAgency: Boolean?,
    @SerializedName("tags") val tags: List<String>?
)