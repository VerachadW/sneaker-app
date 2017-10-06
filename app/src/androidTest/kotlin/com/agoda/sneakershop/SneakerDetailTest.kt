package com.agoda.sneakershop

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
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
    fun checkFirstItemDetail() {
        onView(withId(R.id.rvSneakerList))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tvSneakerDetailName)).check(matches(withText("Nike SF Air Force 1 Mid")))
        onView(withId(R.id.tvSneakerDetailCollectiomName)).check(matches(withText("Air Force 1")))
    }

}