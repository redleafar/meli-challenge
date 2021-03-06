package co.meli.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Installments(
    @SerializedName("quantity") val quantity: Int?,
    @SerializedName("amount") val amount: Double?,
    @SerializedName("rate") val rate: Double?,
    @SerializedName("currency_id") val currencyId: String?
) : Serializable