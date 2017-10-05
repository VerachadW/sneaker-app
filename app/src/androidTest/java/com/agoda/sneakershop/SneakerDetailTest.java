package com.agoda.sneakershop;

import com.agoda.sneakershop.screen.sneaker.SneakerActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

/**
 * Created by vwongsawangt on 10/5/2017 AD.
 */

@RunWith(AndroidJUnit4.class)
public class SneakerDetailTest {

    @Rule
    public ActivityTestRule<SneakerActivity> mActivityRule = new ActivityTestRule<>(SneakerActivity.class);

    @Test
    public void checkFirstItemDetail() {
        //TODO: Check the first item name and category
    }

    @Test
    public void checkLastItemDetail() {
        //TODO: Check the last item name and category
    }

}
