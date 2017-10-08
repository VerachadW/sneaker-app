package com.agoda.sneakershop.screen.sneaker.detail

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class SneakerDetailViewModel @ParcelConstructor constructor(
        val id: Long,
        val name: String,
        val categoryName: String,
        val collectionName: String,
        val price: Double,
        val imageUrl: String,
        val description: String)