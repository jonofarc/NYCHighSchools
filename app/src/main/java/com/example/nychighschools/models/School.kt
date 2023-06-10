package com.example.nychighschools.models

import kotlinx.serialization.Serializable

@Serializable
data class School(

    val schoolName: String = "",

    val buildingCode: String = "",
    val location: String = "",

    )