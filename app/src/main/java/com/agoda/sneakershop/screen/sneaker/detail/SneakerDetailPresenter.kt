package com.agoda.sneakershop.screen.sneaker.detail

import com.agoda.sneakershop.common.scheduler.SchedulerProvider
import com.agoda.sneakershop.screen.base.BaseLceRxPresenter

class SneakerDetailPresenter(schedulerProvider: SchedulerProvider,
                             private val interactor: SneakerDetailInteractor) :
        BaseLceRxPresenter<SneakerDetailView, SneakerDetailViewModel>(
                schedulerProvider.main(), schedulerProvider.io()) {

    fun refreshData(id: Long, pullToRefresh: Boolean) {
        subscribe(interactor.loadSneakerDetail(id), pullToRefresh)
    }

}