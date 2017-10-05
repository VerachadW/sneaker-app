package com.agoda.sneakershop.data.repository

import com.agoda.sneakershop.data.api.SneakerApi
import com.agoda.sneakershop.data.entity.SneakerDetailEntity
import com.agoda.sneakershop.data.entity.SneakerEntity
import rx.Single

interface SneakerRepository {
    fun observeSneakers(): Single<List<SneakerEntity>>
    fun observeSneakerDetail(id: Long): Single<SneakerDetailEntity>
}

class SneakerRepositoryImpl(private val sneakerApi: SneakerApi) : SneakerRepository {

    override fun observeSneakers(): Single<List<SneakerEntity>> = sneakerApi.getSneakers()

    override fun observeSneakerDetail(id: Long): Single<SneakerDetailEntity> = sneakerApi.getSneakerDetail(id)

}