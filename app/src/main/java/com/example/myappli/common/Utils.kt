package com.example.myappli.common

import android.content.Context
import android.util.Log
import com.example.myappli.model.Category
import com.example.myappli.model.Tag
import com.example.myappli.model.demo.Features
import com.example.myappli.model.demo.Json4
import com.google.gson.GsonBuilder
import java.io.IOException
import java.util.*

private const val TAG = "Utils"

fun listFromJsonString(json: String?): List<Tag> {
    return GsonBuilder().create().fromJson(json, Array<Tag>::class.java).toList()
}
fun getCategoryString(json: String?): List<Category> {
    return GsonBuilder().create().fromJson(json, Array<Category>::class.java).toList()
}


fun getJson4Kotlin(json: String?): Json4 {
    Log.d(TAG, "getQuestionBankListFromJsonString: $json")
    val questionBank = GsonBuilder().create().fromJson(json, Json4::class.java)
    Log.d(TAG, "getQuestionBankListFromJsonString: $questionBank")
    return questionBank
}


fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        Log.d(TAG, "getJsonDataFromAsset: $jsonString")
    } catch (ioException: IOException) {
        Log.e(TAG, "getJsonDataFromAsset: ", ioException)
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

