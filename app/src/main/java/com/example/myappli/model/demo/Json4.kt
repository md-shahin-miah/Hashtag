package com.example.myappli.model.demo

import com.google.gson.annotations.SerializedName


data class Json4 (

    @SerializedName("type") val type : String,
    @SerializedName("name") val name : String,
    @SerializedName("crs") val crs : Crs,
    @SerializedName("features") val features : List<Features>
)