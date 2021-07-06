package co.meli.domain.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") val id: String?,
    @SerializedName("site_id") val siteId: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("seller") val seller: Seller?,
    @SerializedName("price") val price: Double?,
    @SerializedName("currency_id") val currencyId: String?,
    @SerializedName("available_quantity") val availableQuantity: Int?,
    @SerializedName("sold_quantity") val soldQuantity: Int?,
    @SerializedName("buying_mode") val buyingMode: String?,
    @SerializedName("listing_type_id") val listingTypeId: String?,
    @SerializedName("stop_time") val stopTime: String?,
    @SerializedName("condition") val condition: String?,
    @SerializedName("permalink") val permalink: String?,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("accepts_mercadopago") val acceptsMercadoPago: Boolean?,
    @SerializedName("installments") val installments: Installments?,
    @SerializedName("address") val address: Address?,
    @SerializedName("shipping") val shipping: Shipping?,
    @SerializedName("seller_address") val sellerAddress: SellerAddress?,
    @SerializedName("attributes") val attributes: List<Attribute>?,
    @SerializedName("original_price") val originalPrice: Int?,
    @SerializedName("category_id") val categoryId: String?,
    @SerializedName("tags") val tags: List<String>?,
    @SerializedName("catalog_listing") val catalogListing: Boolean?

)