package com.example.myappli.model.demo

import com.google.gson.annotations.SerializedName


data class Properties (

	@SerializedName("category") val category : String,
	@SerializedName("tag_name") val tag_name : String,
	@SerializedName("tag_name_detail") val tag_name_detail : String
)
