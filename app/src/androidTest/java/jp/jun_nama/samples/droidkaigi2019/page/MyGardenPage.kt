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
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.google.samples.apps.sunflower.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.startsWith

object MyGardenPage {

    fun assertPlanted(plantName: String): MyGardenPage {
        val recyclerView = onView(withId(R.id.garden_list))
        recyclerView.perform(scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(startsWith(plantName)))))
        onView(allOf(withId(R.id.plant_date), withText(startsWith(plantName)))).check(matches((isDisplayed())))
        return MyGardenPage
    }

    fun goPlantList(): PlantListPage {
        val appCompatImageButton = Espresso.onView(
                Matchers.allOf(ViewMatchers.withContentDescription("上へ移動"),
                        childAtPosition(
                                Matchers.allOf(ViewMatchers.withId(R.id.toolbar),
                                        childAtPosition(
                                                ViewMatchers.withId(R.id.appbar),
                                                0)),
                                1),
                        ViewMatchers.isDisplayed()))
        appCompatImageButton.perform(ViewActions.click())

        val navigationMenuItemView = Espresso.onView(
                Matchers.allOf(childAtPosition(
                        Matchers.allOf(ViewMatchers.withId(R.id.design_navigation_view),
                                childAtPosition(
                                        ViewMatchers.withId(R.id.navigation_view),
                                        0)),
                        2),
                        ViewMatchers.isDisplayed()))
        navigationMenuItemView.perform(ViewActions.click())
        return PlantListPage
    }
}