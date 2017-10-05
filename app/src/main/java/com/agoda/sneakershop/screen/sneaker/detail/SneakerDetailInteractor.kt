package com.agoda.sneakershop.screen.sneaker.detail

import com.agoda.sneakershop.data.repository.SneakerRepository
import rx.Observable

open class SneakerDetailInteractor(private val sneakerRepository: SneakerRepository,
                                   private val viewModelMapper: SneakerDetailViewModelMapper) {

    open fun loadSneakerDetail(id: Long): Observable<SneakerDetailViewModel> =
            sneakerRepository.observeSneakerDetail(id).map(viewModelMapper::map).toObservable()

}