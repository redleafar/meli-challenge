package co.meli.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class State(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?
) : Serializable