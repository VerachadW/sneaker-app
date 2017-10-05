package com.agoda.sneakershop.screen.sneaker.list

import com.agoda.sneakershop.data.repository.SneakerRepository
import rx.Observable

open class SneakerListInteractor(
        private val sneakerRepository: SneakerRepository,
        private val viewModelMapper: SneakerListViewModelMapper) {

    open fun loadSneakers(): Observable<SneakerListViewModel> =
            sneakerRepository.observeSneakers().map(viewModelMapper::map).toObservable()
}