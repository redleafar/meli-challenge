package co.meli.domain.models

import com.google.gson.annotations.SerializedName

data class Paging(
    @SerializedName("total") val total: Int?,
    @SerializedName("offset") val offset: Int?,
    @SerializedName("limit") val limit: Int?,
    @SerializedName("primaryResults") val primaryResuls: Int?
)