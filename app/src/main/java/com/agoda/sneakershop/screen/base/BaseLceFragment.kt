package com.agoda.sneakershop.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment

abstract class BaseLceFragment<M, V : MvpLceView<M>, P : MvpPresenter<V>> : MvpLceViewStateFragment<View, M, V, P>() {

    lateinit var unbinder: Unbinder

    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String = "Error ${e?.message ?: ""}" //TODO: resourceString
}