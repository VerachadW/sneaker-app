package com.agoda.sneakershop.di

import com.agoda.sneakershop.screen.sneaker.detail.SneakerDetailFragment
import com.agoda.sneakershop.screen.sneaker.list.SneakerListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class
))
interface AppComponent {
    fun inject(sneakerListFragment: SneakerListFragment)
    fun inject(sneakerDetailFragment: SneakerDetailFragment)
}