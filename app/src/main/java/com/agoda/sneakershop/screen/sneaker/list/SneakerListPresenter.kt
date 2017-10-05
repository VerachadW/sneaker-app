package com.agoda.sneakershop.screen.sneaker.list

import com.agoda.sneakershop.common.scheduler.SchedulerProvider
import com.agoda.sneakershop.screen.base.BaseLceRxPresenter

class SneakerListPresenter(
        schedulerProvider: SchedulerProvider,
        private val interactor: SneakerListInteractor)
    : BaseLceRxPresenter<SneakerListView, SneakerListViewModel>(schedulerProvider.main(), schedulerProvider.io()) {

    fun refreshData(pullToRefresh: Boolean) {
        subscribe(interactor.loadSneakers(), pullToRefresh)
    }

}