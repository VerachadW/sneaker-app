/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hannesdorfmann.mosby.mvp.rx.lce;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.rx.scheduler.AndroidSchedulerTransformer;

import rx.Observable;
import rx.Subscriber;

/**
 * A presenter for RxJava, that assumes that only one Observable is subscribed by this presenter.
 * The idea is, that you make your (chain of) Observable and pass it to {@link
 * #subscribe(Observable, boolean)}. The presenter internally subscribes himself as Subscriber to the
 * observable
 * (which executes the observable). Before subscribing the presenter calls
 * {@link #applyScheduler(Observable)} to apply the <code>subscribeOn()</code> and
 * <code>observeOn()</code>
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class MvpLceRxPresenter<V extends MvpLceView<M>, M>
    extends com.hannesdorfmann.mosby.mvp.MvpBasePresenter<V>
    implements com.hannesdorfmann.mosby.mvp.MvpPresenter<V> {

  protected Subscriber<M> subscriber;

  /**
   * Unsubscribes the subscriber and set it to null
   */
  protected void unsubscribe() {
    if (subscriber != null && !subscriber.isUnsubscribed()) {
      subscriber.unsubscribe();
    }

    subscriber = null;
  }

  /**
   * Subscribes the presenter himself as subscriber on the observable
   *
   * @param observable The observable to subscribe
   * @param pullToRefresh if true - request without loading animation, if error - show light error without blocking content
   */
  public void subscribe(Observable<M> observable, final boolean pullToRefresh) {
    subscribe(observable, pullToRefresh, pullToRefresh);
  }

  /**
   * Subscribes the presenter himself as subscriber on the observable
   *
   * @param observable The observable to subscribe
   * @param showLightError show light error without blocking content
   * @param pullToRefresh if true - request without loading animation
   */
  public void subscribe(Observable<M> observable, final boolean showLightError, final boolean pullToRefresh) {
    if (getView() != null) {
      getView().showLoading(pullToRefresh);
    }

    unsubscribe();

    subscriber = new Subscriber<M>() {
      private boolean ptr = showLightError;

      @Override
      public void onCompleted() {
        MvpLceRxPresenter.this.onCompleted();
      }

      @Override
      public void onError(Throwable e) {
        MvpLceRxPresenter.this.onError(e, ptr);
      }

      @Override
      public void onNext(M m) {
        MvpLceRxPresenter.this.onNext(m);
      }
    };

    observable = applyScheduler(observable);
    observable.subscribe(subscriber);
  }

  /**
   * Called in {@link #subscribe(Observable, boolean)} to set  <code>subscribeOn()</code> and
   * <code>observeOn()</code>. As default it uses {@link AndroidSchedulerTransformer}. Override
   * this
   * method if you want to provide your own scheduling implementation.
   */
  protected Observable<M> applyScheduler(Observable<M> observable) {
    return (Observable<M>) observable.compose(new AndroidSchedulerTransformer<>());
  }

  protected void onCompleted() {
    if (getView() != null) {
      getView().showContent();
    }
    unsubscribe();
  }

  protected void onError(Throwable e, boolean pullToRefresh) {
    if (getView() != null) {
      getView().showError(e, pullToRefresh);
    }
    unsubscribe();
  }

  protected void onNext(M data) {
    if (getView() != null) {
      getView().setData(data);
    }
  }

  @Override
  public void detachView(boolean retainInstance) {
    super.detachView(retainInstance);
    if (!retainInstance) {
      unsubscribe();
    }
  }
}
