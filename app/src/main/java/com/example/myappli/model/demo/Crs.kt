package com.example.myappli.model.demo

import com.google.gson.annotations.SerializedName


data class Crs (

	@SerializedName("type") val type : String,
	@SerializedName("properties") val properties : Properties
)