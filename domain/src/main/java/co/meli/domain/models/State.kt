package co.meli.domain.models

import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?
)