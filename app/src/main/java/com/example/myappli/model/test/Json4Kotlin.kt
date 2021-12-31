package com.example.myappli.model.test

import com.google.gson.annotations.SerializedName


data class Json4Kotlin (

	@SerializedName("type") val type : String,
	@SerializedName("name") val name : String,
	@SerializedName("crs") val crs : Crs,
	@SerializedName("features") val features : List<Features>
)