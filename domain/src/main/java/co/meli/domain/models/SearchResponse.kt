package co.meli.domain.models

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("site_id") val siteId: String?,
    @SerializedName("query") val query: String?,
    @SerializedName("paging") val paging: Paging?,
    @SerializedName("results") val results: List<Product> = emptyList()
)
