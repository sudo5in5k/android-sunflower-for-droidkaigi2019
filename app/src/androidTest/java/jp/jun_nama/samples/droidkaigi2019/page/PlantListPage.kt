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

package jp.jun_nama.samples.droidkaigi2019.page

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.google.samples.apps.sunflower.R
import org.hamcrest.Matchers

fun showPlantDetail(plantName: String) {
    val recyclerView = Espresso.onView(
            Matchers.allOf(ViewMatchers.withId(R.id.plant_list),
                    childAtPosition(
                            ViewMatchers.withId(R.id.garden_nav_fragment),
                            0),
                    ViewMatchers.isDisplayed()))
    recyclerView.perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(ViewMatchers.hasDescendant(ViewMatchers.withText(plantName)), ViewActions.click()))
}

fun goBackMyGarden() {
    val appCompatImageButton3 = Espresso.onView(
            Matchers.allOf(ViewMatchers.withContentDescription("上へ移動"),
                    childAtPosition(
                            Matchers.allOf(ViewMatchers.withId(R.id.toolbar),
                                    childAtPosition(
                                            ViewMatchers.withId(R.id.appbar),
                                            0)),
                            1),
                    ViewMatchers.isDisplayed()))
    appCompatImageButton3.perform(ViewActions.click())
}