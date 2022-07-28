package com.example.navermap


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapResponse(
    @SerialName("addresses")
    val addresses: List<Addresse?>?,
    @SerialName("errorMessage")
    val errorMessage: String?,
    @SerialName("meta")
    val meta: Meta?,
    @SerialName("status")
    val status: String?
)


@Serializable
data class Addresse(
    @SerialName("addressElements")
    val addressElements: List<AddressElement?>?,
    @SerialName("distance")
    val distance: Double?,
    @SerialName("englishAddress")
    val englishAddress: String?,
    @SerialName("jibunAddress")
    val jibunAddress: String?,
    @SerialName("roadAddress")
    val roadAddress: String?,
    @SerialName("x")
    val x: String?,
    @SerialName("y")
    val y: String?
)

@Serializable
data class AddressElement(
    @SerialName("code")
    val code: String?,
    @SerialName("longName")
    val longName: String?,
    @SerialName("shortName")
    val shortName: String?,
    @SerialName("types")
    val types: List<String?>?
)

@Serializable
data class Meta(
    @SerialName("count")
    val count: Int?,
    @SerialName("page")
    val page: Int?,
    @SerialName("totalCount")
    val totalCount: Int?
)