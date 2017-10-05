package com.agoda.sneakershop.di

import com.agoda.sneakershop.BuildConfig
import com.agoda.sneakershop.common.scheduler.SchedulerProvider
import com.agoda.sneakershop.common.scheduler.SchedulerProviderImpl
import com.agoda.sneakershop.data.api.SneakerApi
import com.agoda.sneakershop.data.repository.*
import com.agoda.sneakershop.screen.sneaker.detail.SneakerDetailInteractor
import com.agoda.sneakershop.screen.sneaker.detail.SneakerDetailPresenter
import com.agoda.sneakershop.screen.sneaker.detail.SneakerDetailViewModelMapper
import com.agoda.sneakershop.screen.sneaker.detail.SneakerDetailViewModelMapperImpl
import dagger.Module
import javax.inject.Singleton
import com.agoda.sneakershop.screen.sneaker.list.SneakerListPresenter
import com.agoda.sneakershop.screen.sneaker.list.SneakerListViewModelMapper
import com.agoda.sneakershop.screen.sneaker.list.SneakerListViewModelMapperImpl
import com.agoda.sneakershop.screen.sneaker.list.SneakerListInteractor
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


@Module
class AppModule {
    //Scheduler
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()

    //Retrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().apply {
        baseUrl("https://sneakerstoreapi.herokuapp.com/api/v1/")
        if (BuildConfig.DEBUG) {
            client(run {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder().addInterceptor(interceptor).build()
            })
        }
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJavaCallAdapterFactory.create())
    }.build()

    @Provides
    fun provideSneakerApi(retrofit: Retrofit): SneakerApi = retrofit.create(SneakerApi::class.java)

    //Repository
    @Provides
    fun provideSneakerRepository(sneakerApi: SneakerApi): SneakerRepository = SneakerRepositoryImpl(sneakerApi)

    //Interactor
    @Provides
    fun provideSneakerListInteractor(sneakerRepository: SneakerRepository, viewModelMapper: SneakerListViewModelMapper): SneakerListInteractor
            = SneakerListInteractor(sneakerRepository, viewModelMapper)

    @Provides
    fun provideSneakerDetailInteractor(sneakerRepository: SneakerRepository, viewModelMapper: SneakerDetailViewModelMapper): SneakerDetailInteractor
            = SneakerDetailInteractor(sneakerRepository, viewModelMapper)

    //Mapper
    @Provides
    fun provideSneakerListViewModelMapper(): SneakerListViewModelMapper = SneakerListViewModelMapperImpl()

    @Provides
    fun provideSneakerDetailViewModelMapper(): SneakerDetailViewModelMapper = SneakerDetailViewModelMapperImpl()

    //Presenter
    @Provides
    fun provideSneakerListPresenter(schedulerProvider: SchedulerProvider,
                                    sneakerListInteractor: SneakerListInteractor)
            = SneakerListPresenter(schedulerProvider, sneakerListInteractor)

    @Provides
    fun provideSneakerDetailPresenter(schedulerProvider: SchedulerProvider,
                                      sneakerDetailInteractor: SneakerDetailInteractor)
            = SneakerDetailPresenter(schedulerProvider, sneakerDetailInteractor)

}