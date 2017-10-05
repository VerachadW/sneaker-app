package com.agoda.sneakershop.screen.sneaker.list

import org.parceler.Parcel

@Parcel
data class SneakerListViewModel(
        val items: List<SneakerListItemViewModel> = listOf()
)

@Parcel
data class SneakerListItemViewModel(
        val id: Long = 0L,
        val name: String = "",
        val categoryName: String = "",
        val collectionName: String = "",
        val price: Double = 0.0,
        val imageUrl: String = ""
)
