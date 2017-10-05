package com.agoda.sneakershop.screen.sneaker.detail

import com.agoda.sneakershop.data.entity.SneakerDetailEntity

interface SneakerDetailViewModelMapper {
    fun map(sneakerDetailEntity: SneakerDetailEntity): SneakerDetailViewModel
}

class SneakerDetailViewModelMapperImpl : SneakerDetailViewModelMapper {

    override fun map(sneakerDetailEntity: SneakerDetailEntity): SneakerDetailViewModel
            = SneakerDetailViewModel(
            id = sneakerDetailEntity.id,
            name = sneakerDetailEntity.name,
            categoryName = sneakerDetailEntity.categoryName,
            collectionName = sneakerDetailEntity.collectionName,
            price = sneakerDetailEntity.price,
            imageUrl = sneakerDetailEntity.imageUrl,
            description = sneakerDetailEntity.description ?: "")

}