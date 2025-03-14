/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.horologist.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.foundation.rememberSwipeToDismissBoxState
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import androidx.wear.compose.navigation.rememberSwipeDismissableNavHostState
import com.google.android.horologist.audio.ui.VolumeScreen
import com.google.android.horologist.composables.DatePicker
import com.google.android.horologist.composables.TimePicker
import com.google.android.horologist.composables.TimePickerWith12HourClock
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults
import com.google.android.horologist.compose.navscaffold.NavScaffoldViewModel
import com.google.android.horologist.compose.navscaffold.WearNavScaffold
import com.google.android.horologist.compose.navscaffold.composable
import com.google.android.horologist.compose.navscaffold.lazyListComposable
import com.google.android.horologist.compose.navscaffold.scrollStateComposable
import com.google.android.horologist.compose.navscaffold.scrollable
import com.google.android.horologist.datalayer.DataLayerNodesScreen
import com.google.android.horologist.datalayer.DataLayerNodesViewModel
import com.google.android.horologist.networks.NetworkScreen
import com.google.android.horologist.pager.SamplePagerScreen
import com.google.android.horologist.paging.PagingItemScreen
import com.google.android.horologist.paging.PagingScreen
import com.google.android.horologist.rotary.RotaryMenuScreen
import com.google.android.horologist.rotary.RotaryScrollScreen
import com.google.android.horologist.rotary.RotaryScrollWithFlingOrSnapScreen
import com.google.android.horologist.sectionedlist.SectionedListMenuScreen
import com.google.android.horologist.sectionedlist.expandable.SectionedListExpandableScreen
import com.google.android.horologist.sectionedlist.stateful.SectionedListStatefulScreen
import com.google.android.horologist.sectionedlist.stateless.SectionedListStatelessScreen
import java.time.LocalDateTime

@Composable
fun SampleWearApp() {
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState()
    val navHostState =
        rememberSwipeDismissableNavHostState(swipeToDismissBoxState = swipeToDismissBoxState)
    val navController = rememberSwipeDismissableNavController()

    var time by remember { mutableStateOf(LocalDateTime.now()) }

    WearNavScaffold(
        startDestination = Screen.Menu.route,
        navController = navController,
        state = navHostState,
    ) {
        scrollable(
            route = Screen.Menu.route,
        ) {
            MenuScreen(
                navigateToRoute = { route -> navController.navigate(route) },
                time = time,
                columnState = it.columnState,
            )
        }
        scrollable(
            Screen.DataLayerNodes.route,
        ) {
            DataLayerNodesScreen(
                viewModel = viewModel(factory = DataLayerNodesViewModel.Factory),
                columnState = it.columnState,
            )
        }
        scrollable(
            Screen.Network.route,
            columnStateFactory = ScalingLazyColumnDefaults.belowTimeText(firstItemIsFullWidth = true),
        ) {
            NetworkScreen(
                columnState = it.columnState,
            )
        }
        composable(Screen.FillMaxRectangle.route) {
            FillMaxRectangleScreen()
        }
        composable(Screen.Volume.route) {
            VolumeScreen()
        }
        lazyListComposable(Screen.ScrollAway.route) {
            ScrollScreenLazyColumn(
                scrollState = it.scrollableState,
            )
        }
        scrollable(
            Screen.ScrollAwaySLC.route,
        ) {
            ScrollAwayScreenScalingLazyColumn(
                columnState = it.columnState,
            )
        }
        scrollStateComposable(
            Screen.ScrollAwayColumn.route,
        ) {
            ScrollAwayScreenColumn(
                scrollState = it.scrollableState,
            )
        }
        composable(Screen.DatePicker.route) {
            it.timeTextMode = NavScaffoldViewModel.TimeTextMode.Off

            DatePicker(
                date = time.toLocalDate(),
                onDateConfirm = {
                    time = time.toLocalTime().atDate(it)
                    navController.popBackStack()
                },
            )
        }
        composable(Screen.TimePicker.route) {
            it.timeTextMode = NavScaffoldViewModel.TimeTextMode.Off

            TimePickerWith12HourClock(
                time = time.toLocalTime(),
                onTimeConfirm = {
                    time = time.toLocalDate().atTime(it)
                    navController.popBackStack()
                },
            )
        }
        composable(Screen.TimeWithSecondsPicker.route) {
            it.timeTextMode = NavScaffoldViewModel.TimeTextMode.Off

            TimePicker(
                time = time.toLocalTime(),
                onTimeConfirm = {
                    time = time.toLocalDate().atTime(it)
                    navController.popBackStack()
                },
            )
        }
        composable(Screen.TimeWithoutSecondsPicker.route) {
            it.timeTextMode = NavScaffoldViewModel.TimeTextMode.Off

            TimePicker(
                time = time.toLocalTime(),
                onTimeConfirm = {
                    time = time.toLocalDate().atTime(it)
                    navController.popBackStack()
                },
                showSeconds = false,
            )
        }
        scrollable(
            route = Screen.SectionedListMenuScreen.route,
        ) {
            SectionedListMenuScreen(
                navigateToRoute = { route -> navController.navigate(route) },
                columnState = it.columnState,
            )
        }
        scrollable(
            Screen.SectionedListStatelessScreen.route,
        ) {
            SectionedListStatelessScreen(
                columnState = it.columnState,
            )
        }
        scrollable(
            Screen.SectionedListStatefulScreen.route,
        ) {
            SectionedListStatefulScreen(
                columnState = it.columnState,
            )
        }
        scrollable(
            Screen.SectionedListExpandableScreen.route,
        ) {
            SectionedListExpandableScreen(
                columnState = it.columnState,
            )
        }
        scrollable(
            route = Screen.RotaryMenuScreen.route,
        ) {
            RotaryMenuScreen(
                navigateToRoute = { route -> navController.navigate(route) },
                columnState = it.columnState,
            )
        }
        composable(route = Screen.RotaryScrollScreen.route) {
            RotaryScrollScreen()
        }
        composable(route = Screen.RotaryScrollReversedScreen.route) {
            RotaryScrollScreen(reverseDirection = true)
        }
        composable(route = Screen.RotaryScrollWithFlingScreen.route) {
            RotaryScrollWithFlingOrSnapScreen(isFling = true, isSnap = false)
        }
        composable(route = Screen.RotarySnapListScreen.route) {
            RotaryScrollWithFlingOrSnapScreen(isFling = false, isSnap = true)
        }
        scrollable(
            route = Screen.Paging.route,
            columnStateFactory = ScalingLazyColumnDefaults.belowTimeText(firstItemIsFullWidth = true),
        ) {
            PagingScreen(navController = navController, columnState = it.columnState)
        }
        composable(
            route = Screen.PagingItem.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                },
            ),
        ) {
            PagingItemScreen(it.arguments!!.getInt("id"))
        }
        composable(route = Screen.PagerScreen.route) {
            SamplePagerScreen(swipeToDismissBoxState)
        }
    }
}
