package com.agoda.sneakershop

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.agoda.sneakershop.screen.sneaker.SneakerActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by vwongsawangt on 10/6/2017 AD.
 */
@RunWith(AndroidJUnit4::class)
class SneakerDetailTest {

    @JvmField
    @Rule
    val rule = ActivityTestRule(SneakerActivity::class.java)

    @Test
    fun testFirstItemDetail() {
        KView(R.id.rvSneakerList) perform {
            clicOnItem(0)
        }

        KView(R.id.tvSneakerDetailName) check {
            hasText("Nike SF Air Force 1 Mid")
        }

        KView(R.id.tvSneakerDetailCollectiomName) check {
            hasText("Air Force 1")
        }
    }
}