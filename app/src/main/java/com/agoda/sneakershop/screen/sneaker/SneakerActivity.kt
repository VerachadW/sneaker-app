package com.agoda.sneakershop.screen.sneaker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agoda.sneakershop.R
import com.agoda.sneakershop.common.extension.transaction
import com.agoda.sneakershop.screen.sneaker.detail.SneakerDetailFragment
import com.agoda.sneakershop.screen.sneaker.list.SneakerListFragment
import kotlinx.android.synthetic.main.activity_sneaker.*

class SneakerActivity : AppCompatActivity(), SneakerListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sneaker)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null)
            supportFragmentManager.transaction {
                add(R.id.contentContainer, SneakerListFragment())
            }
    }

    override fun openSneakerDetail(sneakerId: Long) {
        supportFragmentManager.transaction {
            add(R.id.contentContainer, SneakerDetailFragment.instance(sneakerId))
            addToBackStack(null)
        }
    }
}
