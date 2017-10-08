package com.agoda.sneakershop.screen.sneaker.list

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class SneakerListViewModel @ParcelConstructor constructor(
        val items: List<SneakerListItemViewModel>
)

@Parcel(Parcel.Serialization.BEAN)
data class SneakerListItemViewModel @ParcelConstructor constructor(
        val id: Long,
        val name: String,
        val categoryName: String,
        val collectionName: String,
        val price: Double,
        val imageUrl: String
)
