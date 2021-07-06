package co.meli.domain.models

import com.google.gson.annotations.SerializedName

data class ValueStruct(
    @SerializedName("number") val number: Double,
    @SerializedName("unit") val unit: String
)