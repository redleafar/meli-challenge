package co.meli.domain.models

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("state_id") val stateId: String?,
    @SerializedName("state_name") val stateName: String?,
    @SerializedName("city_id") val cityId: String?,
    @SerializedName("city_name") val cityName: String?
)