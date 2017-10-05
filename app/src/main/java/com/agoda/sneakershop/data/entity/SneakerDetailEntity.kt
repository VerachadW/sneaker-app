package com.agoda.sneakershop.data.entity

import com.google.gson.annotations.SerializedName

data class SneakerDetailEntity(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("name") val name: String = "",
        @SerializedName("categoryId") val categoryId: Long = 0L,
        @SerializedName("categoryName") val categoryName: String = "",
        @SerializedName("collectionId") val collectionId: Long = 0L,
        @SerializedName("collectionName") val collectionName: String = "",
        @SerializedName("price") val price: Double = 0.0,
        @SerializedName("imageUrl") val imageUrl: String = "",
        @SerializedName("description") val description: String? = null
)
