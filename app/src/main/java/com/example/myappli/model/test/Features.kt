package com.example.myappli.model.test

import com.google.gson.annotations.SerializedName



data class Features (

	@SerializedName("type") val type : String,
	@SerializedName("properties") val properties : Properties,
	@SerializedName("geometry") val geometry : String
)