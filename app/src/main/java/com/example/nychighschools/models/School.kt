package com.example.nychighschools.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class School(
    @SerialName("dbn")
    val dbn: String = "",
    @SerialName("school_name")
    val schoolName: String = "",
    @SerialName("location")
    val location: String = "",
    @SerialName("building_code")
    val buildingCode: String = "",
)

