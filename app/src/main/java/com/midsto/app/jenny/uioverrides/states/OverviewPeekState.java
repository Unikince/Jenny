/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.midsto.app.jenny.uioverrides.states;

import com.midsto.app.jenny.Launcher;
import com.midsto.app.jenny.LauncherState;
import com.midsto.app.jenny.R;
import com.midsto.app.jenny.anim.AnimatorSetBuilder;

import static com.midsto.app.jenny.anim.AnimatorSetBuilder.ANIM_OVERVIEW_FADE;
import static com.midsto.app.jenny.anim.AnimatorSetBuilder.ANIM_OVERVIEW_SCRIM_FADE;
import static com.midsto.app.jenny.anim.AnimatorSetBuilder.ANIM_OVERVIEW_TRANSLATE_X;
import static com.midsto.app.jenny.anim.Interpolators.FAST_OUT_SLOW_IN;
import static com.midsto.app.jenny.anim.Interpolators.INSTANT;
import static com.midsto.app.jenny.anim.Interpolators.OVERSHOOT_1_7;

public class OverviewPeekState extends OverviewState {
    public OverviewPeekState(int id) {
        super(id);
    }

    @Override
    public ScaleAndTranslation getOverviewScaleAndTranslation(Launcher launcher) {
        ScaleAndTranslation result = super.getOverviewScaleAndTranslation(launcher);
        result.translationX = NORMAL.getOverviewScaleAndTranslation(launcher).translationX
                - launcher.getResources().getDimension(R.dimen.overview_peek_distance);
        return result;
    }

    @Override
    public void prepareForAtomicAnimation(Launcher launcher, LauncherState fromState,
            AnimatorSetBuilder builder) {
        if (this == OVERVIEW_PEEK && fromState == NORMAL) {
            builder.setInterpolator(ANIM_OVERVIEW_FADE, INSTANT);
            builder.setInterpolator(ANIM_OVERVIEW_TRANSLATE_X, OVERSHOOT_1_7);
            builder.setInterpolator(ANIM_OVERVIEW_SCRIM_FADE, FAST_OUT_SLOW_IN);
        }
    }
}
