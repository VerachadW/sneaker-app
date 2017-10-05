package com.agoda.sneakershop.screen.base;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.rx.lce.MvpLceRxPresenter;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;

public class BaseLceRxPresenter<V extends MvpLceView<M>, M> extends MvpLceRxPresenter<V, M> {

    protected final Scheduler mainScheduler;
    protected final Scheduler ioScheduler;

    public BaseLceRxPresenter(Scheduler mainScheduler, Scheduler ioScheduler) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
    }

    protected M viewModel;

    public M getViewModel() {
        return viewModel;
    }

    public void setViewModel(M viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected Observable<M> applyScheduler(Observable<M> observable) {
        return observable.subscribeOn(ioScheduler)
                         .observeOn(mainScheduler, true);
    }

    protected void ifViewAttached(Action1<V> action) {
        if (isViewAttached()) {
            action.call(getView());
        }
    }

    public boolean isSubscribing() {
        return subscriber != null && !subscriber.isUnsubscribed();
    }
}