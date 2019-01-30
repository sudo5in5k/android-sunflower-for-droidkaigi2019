/*
 * Copyright (C) 2019 TOYAMA Sumio <jun.nama@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.jun_nama.samples.droidkaigi2019


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.samples.apps.sunflower.GardenActivity
import com.google.samples.apps.sunflower.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.startsWith
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class GardenActivityTest {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<GardenActivity>()

    @Test
    fun gardenActivityTest() {
        val appCompatImageButton = onView(
                allOf(withContentDescription("上へ移動"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.appbar),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton.perform(click())

        val navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.navigation_view),
                                        0)),
                        2),
                        isDisplayed()))
        navigationMenuItemView.perform(click())

        val recyclerView = onView(
                allOf(withId(R.id.plant_list),
                        childAtPosition(
                                withId(R.id.garden_nav_fragment),
                                0),
                        isDisplayed()))
        recyclerView.perform(actionOnItem<RecyclerView.ViewHolder>(hasDescendant(withText("Avocado")), click()))

        val floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.garden_nav_fragment),
                                        0),
                                2),
                        isDisplayed()))
        floatingActionButton.perform(click())

        val appCompatImageButton2 = onView(
                allOf(withContentDescription("上へ移動"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.appbar),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton2.perform(click())

        val appCompatImageButton3 = onView(
                allOf(withContentDescription("上へ移動"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.appbar),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton3.perform(click())

        val textView = onView(
                allOf(withId(R.id.plant_date), withText(startsWith("Avocado")),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.garden_list),
                                        0),
                                1),
                        isDisplayed()))
        textView.check(matches(withText(startsWith("Avocado"))))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
