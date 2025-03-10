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

package com.google.android.horologist.compose.material

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.VolumeDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.google.android.horologist.compose.tools.WearPreview

@WearPreview
@Composable
fun IconPreview() {
    Icon(
        imageVector = Icons.AutoMirrored.Outlined.VolumeDown,
        contentDescription = "contentDescription",
    )
}

@WearPreview
@Composable
fun IconPreviewMirrored() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.VolumeDown,
            contentDescription = "contentDescription",
            rtlMode = IconRtlMode.Mirrored,
        )
    }
}
