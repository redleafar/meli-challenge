package co.meli.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Attribute(
    @SerializedName("name") val name: String?,
    @SerializedName("value_id") val valueId: String?,
    @SerializedName("value_name") val valueName: String?,
    @SerializedName("value_struct") val valueStruct: ValueStruct?,
    @SerializedName("attribute_group_id") val attributeGroupId: String?,
    @SerializedName("attribute_group_name") val attributeGroupName: String?,
    @SerializedName("source") val source: Long?,
    @SerializedName("id") val id: String?
) : Serializable