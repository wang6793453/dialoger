/*
 * Copyright (C) 2017 zhengjun, fanwe (http://www.fanwe.com)
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
package com.fanwe.lib.dialoger.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * 缩放
 */
public class ScaleXYCreater extends BaseAnimatorCreater
{
    private ObjectAnimator[] getObjectAnimator(float... values)
    {
        final ObjectAnimator[] animators = new ObjectAnimator[2];

        final ObjectAnimator scaleX = new ObjectAnimator();
        scaleX.setPropertyName(View.SCALE_X.getName());
        scaleX.setFloatValues(values);

        final ObjectAnimator scaleY = new ObjectAnimator();
        scaleY.setPropertyName(View.SCALE_Y.getName());
        scaleY.setFloatValues(values);

        animators[0] = scaleX;
        animators[1] = scaleY;
        return animators;
    }

    @Override
    protected Animator onCreateAnimator(boolean show, View view)
    {
        final ObjectAnimator[] animators = show ? getObjectAnimator(0, 1.0f) : getObjectAnimator(1.0f, 0);
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animators[0]).with(animators[1]);
        animatorSet.setTarget(view);
        return animatorSet;
    }

    @Override
    protected void onAnimationStart(boolean show, View view)
    {

    }

    @Override
    protected void onAnimationEnd(boolean show, View view)
    {
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
    }
}
