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


import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.samples.apps.sunflower.GardenActivity
import jp.jun_nama.samples.droidkaigi2019.page.MyGardenPage
import jp.jun_nama.samples.droidkaigi2019.page.MyGardenPage.assertPlanted
import jp.jun_nama.samples.droidkaigi2019.page.MyGardenPage.goPlantList
import jp.jun_nama.samples.droidkaigi2019.page.PlantDetailPage.addToMyGarden
import jp.jun_nama.samples.droidkaigi2019.page.PlantDetailPage.goBackPlantList
import jp.jun_nama.samples.droidkaigi2019.page.PlantListPage.goBackMyGarden
import jp.jun_nama.samples.droidkaigi2019.page.PlantListPage.showPlantDetail
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
        MyGardenPage.goPlantList()
                .showPlantDetail("Sunflower")
                .addToMyGarden()
                .goBackPlantList()
                .showPlantDetail("Avocado")
                .addToMyGarden()
                .goBackPlantList()
                .goBackMyGarden()
                .assertPlanted("Sunflower")
                .assertPlanted("Avocado")
    }
}

