package com.agoda.sneakershop.screen.sneaker.list

import com.agoda.sneakershop.data.entity.SneakerEntity
import com.agoda.sneakershop.data.repository.SneakerRepository
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.amshove.kluent.When
import org.amshove.kluent.calling
import org.amshove.kluent.itReturns
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import rx.Single

@RunWith(MockitoJUnitRunner::class)
class SneakerListInteractorTest {

    @Mock
    lateinit var sneakerRepository: SneakerRepository
    @Mock
    lateinit var viewModelMapper: SneakerListViewModelMapper

    lateinit var interactor: SneakerListInteractor

    @Before
    fun setup() {
        interactor = SneakerListInteractor(sneakerRepository, viewModelMapper)
    }

    @Test
    fun loadSneakers() {
        //#region Arrange
        val sneakers = listOf(SneakerEntity())
        val viewModel = SneakerListViewModel(listOf(SneakerListItemViewModel()))
        When calling sneakerRepository.observeSneakers() itReturns Single.just(sneakers)
        whenever(viewModelMapper.map(sneakers)).thenReturn(viewModel)
        //#endregion

        //#region Act
        val actualViewModel = interactor.loadSneakers().test()
        //#endregion

        //#region Assert
        actualViewModel.assertValue(viewModel)
        actualViewModel.assertNoErrors()
        verify(sneakerRepository).observeSneakers()
        verify(viewModelMapper).map(sneakers)
        //#endregion
    }

}