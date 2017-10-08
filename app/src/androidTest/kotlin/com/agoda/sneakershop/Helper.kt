package com.agoda.sneakershop

import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView

/**
 * Created by birth on 10/8/2017.
 */

class KView(layoutId: Int) {
    val interaction: ViewInteraction = Espresso.onView(ViewMatchers.withId(layoutId))
}

class KViewActions(private val interaction: ViewInteraction) {
    fun clicOnItem(position: Int) {
        interaction.perform(RecyclerViewActions.
                    actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.click()))
    }
}

class KViewAssertions(private val interaction: ViewInteraction) {
    fun hasText(text: String) {
        interaction.check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }
}

infix fun KView.perform(func: KViewActions.() -> Unit) {
   func.invoke(KViewActions(interaction))
}

infix fun KView.check(func: KViewAssertions.() -> Unit) {
    func.invoke(KViewAssertions(interaction))
}