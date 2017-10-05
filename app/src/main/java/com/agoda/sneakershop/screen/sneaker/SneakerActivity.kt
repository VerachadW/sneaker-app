package com.agoda.sneakershop.screen.sneaker

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

import com.agoda.sneakershop.R
import com.agoda.sneakershop.common.extension.transaction
import com.agoda.sneakershop.screen.sneaker.detail.SneakerDetailFragment
import com.agoda.sneakershop.screen.sneaker.list.SneakerListFragment

class SneakerActivity : AppCompatActivity(), SneakerListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sneaker)
        setSupportActionBar(findViewById(R.id.toolbar) as Toolbar)
        if (savedInstanceState == null)
            supportFragmentManager.transaction {
                add(R.id.contentContainer, SneakerListFragment())
            }
    }

    override fun openSneakerDetail(sneakerId: Long?) {
        supportFragmentManager.transaction {
            add(R.id.contentContainer, SneakerDetailFragment.instance(sneakerId!!))
            addToBackStack(null)
        }
    }
}
