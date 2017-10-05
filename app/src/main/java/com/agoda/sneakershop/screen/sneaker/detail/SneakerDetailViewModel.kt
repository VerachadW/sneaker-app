package com.agoda.sneakershop.screen.sneaker.detail

import org.parceler.Parcel

@Parcel
data class SneakerDetailViewModel(val id: Long = 0L,
                                  val name: String = "",
                                  val categoryName: String = "",
                                  val collectionName: String = "",
                                  val price: Double = 0.0,
                                  val imageUrl: String = "",
                                  val description: String = "")