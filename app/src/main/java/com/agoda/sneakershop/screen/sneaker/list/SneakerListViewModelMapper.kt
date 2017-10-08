package com.agoda.sneakershop.screen.sneaker.list

import com.agoda.sneakershop.data.entity.SneakerEntity

interface SneakerListViewModelMapper {
    fun map(sneakerEntities: List<SneakerEntity>): SneakerListViewModel
}

class SneakerListViewModelMapperImpl : SneakerListViewModelMapper {
    override fun map(sneakerEntities: List<SneakerEntity>): SneakerListViewModel
            = SneakerListViewModel(sneakerEntities.map { sneaker ->
        SneakerListItemViewModel(id = sneaker.id, name = sneaker.name,
                categoryName = sneaker.categoryName,
                collectionName = sneaker.collectionName,
                imageUrl = sneaker.imageUrl,
                price = sneaker.price)
    })
}
