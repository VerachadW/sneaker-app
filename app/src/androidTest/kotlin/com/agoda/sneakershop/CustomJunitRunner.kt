package com.agoda.sneakershop

import android.support.test.runner.AndroidJUnitRunner
import com.squareup.rx.idler.RxIdler
import rx.plugins.RxJavaPlugins

/**
 * Created by vwongsawangt on 10/7/2017 AD.
 */

class CustomJunitRunner : AndroidJUnitRunner() {

    override fun onStart() {
        RxJavaPlugins.getInstance().registerSchedulersHook(RxIdler.hooks())
        super.onStart()
    }
}