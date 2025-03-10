/*
 * Copyright 2023 The Android Open Source Project
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

package com.google.android.horologist.auth.sample.screens.oauth.devicegrant.signout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Text
import com.google.android.horologist.auth.sample.R
import com.google.android.horologist.compose.material.Confirmation

@Composable
fun DeviceGrantSignOutScreen(
    navController: NavHostController,
    viewModel: DeviceGrantSignOutViewModel = viewModel(factory = DeviceGrantSignOutViewModel.Factory),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when (state) {
        DeviceGrantSignOutScreenState.Idle -> {
            SideEffect {
                viewModel.onIdleStateObserved()
            }

            LoadingView()
        }

        DeviceGrantSignOutScreenState.Loading -> {
            LoadingView()
        }

        DeviceGrantSignOutScreenState.Success -> {
            Confirmation(
                onTimeout = navController::popBackStack,
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.device_grant_sign_out_success_message),
                )
            }
        }

        DeviceGrantSignOutScreenState.Failed -> {
            SideEffect {
                navController.popBackStack()
            }
        }
    }
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
