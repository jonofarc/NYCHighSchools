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
    @SerialName("phone_number")
    val phoneNumber: String = "",
    @SerialName("school_email")
    val schoolEmail: String = "",
    @SerialName("website")
    val website: String = "",
) {
    val getDisplayLocation: String
        get() {
            val displayLocation = location.split("(")

            return displayLocation[0]
        }
}



