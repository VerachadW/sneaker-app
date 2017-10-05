package com.agoda.sneakershop.screen.sneaker.list

import com.agoda.sneakershop.common.scheduler.SchedulerProvider
import com.nhaarman.mockito_kotlin.verify
import org.amshove.kluent.When
import org.amshove.kluent.calling
import org.amshove.kluent.itReturns
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import rx.Observable
import rx.schedulers.Schedulers

@RunWith(MockitoJUnitRunner::class)
class SneakerListPresenterTest {

    @Mock
    lateinit var sneakerListInteractor: SneakerListInteractor
    @Mock
    lateinit var schedulerProvider: SchedulerProvider
    @Mock
    lateinit var view: SneakerListView

    lateinit var presenter: SneakerListPresenter


    @Before
    fun setup() {
        When calling schedulerProvider.io() itReturns Schedulers.trampoline()
        When calling schedulerProvider.main() itReturns Schedulers.trampoline()
        presenter = SneakerListPresenter(schedulerProvider, sneakerListInteractor)
        presenter.attachView(view)
    }

    @Test
    fun refreshSuccess() {
        //Arrange
        val viewModel = SneakerListViewModel(emptyList())
        When calling sneakerListInteractor.loadSneakers() itReturns Observable.just(viewModel)

        //Act
        presenter.refreshData(false)

        //Assert
        verify(sneakerListInteractor).loadSneakers()
        verify(view).setData(viewModel)
    }

    @Test
    fun refreshError() {
        //Arrange
        val error = Throwable()
        When calling sneakerListInteractor.loadSneakers() itReturns Observable.error(error)

        //Act
        presenter.refreshData(false)

        //Assert
        verify(view).showError(error, false)
    }
}