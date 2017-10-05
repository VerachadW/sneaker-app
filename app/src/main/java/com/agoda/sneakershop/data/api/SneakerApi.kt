package com.agoda.sneakershop.data.api

import com.agoda.sneakershop.data.entity.SneakerDetailEntity
import com.agoda.sneakershop.data.entity.SneakerEntity
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Single

interface SneakerApi {
    @GET("sneakers")
    fun getSneakers(): Single<List<SneakerEntity>>

    @GET("sneaker/{id}")
    fun getSneakerDetail(@Path("id") id: Long): Single<SneakerDetailEntity>
}