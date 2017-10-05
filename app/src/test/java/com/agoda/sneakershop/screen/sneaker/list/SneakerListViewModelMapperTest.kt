package com.agoda.sneakershop.screen.sneaker.list

import com.agoda.sneakershop.data.entity.SneakerEntity
import org.amshove.kluent.shouldEqual
import org.junit.Test

import org.junit.Before

class SneakerListViewModelMapperTest {

    lateinit var sneakerListViewModelMapper: SneakerListViewModelMapper

    @Before
    fun setup() {
        sneakerListViewModelMapper = SneakerListViewModelMapperImpl()
    }

    @Test
    fun map() {
        val sneakers = listOf(
                SneakerEntity(id = 0, name = "name0", categoryId = 0, categoryName = "category0",
                        collectionId = 0, collectionName = "collection0", price = 100.0, imageUrl = "image0"),
                SneakerEntity(id = 1, name = "name1", categoryId = 0, categoryName = "category0",
                        collectionId = 1, collectionName = "collection1", price = 101.0, imageUrl = "image1"),
                SneakerEntity(id = 2, name = "name2", categoryId = 1, categoryName = "category1",
                        collectionId = 0, collectionName = "collection0", price = 102.0, imageUrl = "image2"),
                SneakerEntity(id = 3, name = "name3", categoryId = 1, categoryName = "category1",
                        collectionId = 1, collectionName = "collection2", price = 103.0, imageUrl = "image3"))

        val expectedViewModel = SneakerListViewModel(listOf(
                SneakerListItemViewModel(id = 0, name = "name0", categoryName = "category0",
                        collectionName = "collection0", price = 100.0, imageUrl = "image0"),
                SneakerListItemViewModel(id = 1, name = "name1", categoryName = "category0",
                        collectionName = "collection1", price = 101.0, imageUrl = "image1"),
                SneakerListItemViewModel(id = 2, name = "name2", categoryName = "category1",
                        collectionName = "collection0", price = 102.0, imageUrl = "image2"),
                SneakerListItemViewModel(id = 3, name = "name3", categoryName = "category1",
                        collectionName = "collection2", price = 103.0, imageUrl = "image3")
        ))

        sneakerListViewModelMapper.map(sneakers) shouldEqual expectedViewModel
    }

}