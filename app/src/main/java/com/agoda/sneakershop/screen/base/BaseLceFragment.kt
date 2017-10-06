package com.agoda.sneakershop.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment

abstract class BaseLceFragment<M, V : MvpLceView<M>, P : MvpPresenter<V>> : MvpLceViewStateFragment<View, M, V, P>() {

    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(layoutId, container, false)


    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String = "Error ${e?.message ?: ""}" //TODO: resourceString
}