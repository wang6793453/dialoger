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
import android.animation.AnimatorListenerAdapter;
import android.view.View;

import com.fanwe.lib.dialoger.Dialoger;

public abstract class BaseAnimatorCreater implements Dialoger.AnimatorCreater
{
    @Override
    public final Animator createAnimator(final boolean show, final View view)
    {
        final Animator animator = onCreateAnimator(show, view);
        if (animator != null)
        {
            animator.addListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationStart(Animator animation)
                {
                    super.onAnimationStart(animation);
                    BaseAnimatorCreater.this.onAnimationStart(show, view);
                }

                @Override
                public void onAnimationEnd(Animator animation)
                {
                    super.onAnimationEnd(animation);
                    animation.removeListener(this);
                    BaseAnimatorCreater.this.onAnimationEnd(show, view);
                }
            });
            onCreated(show, view, animator);
        }
        return animator;
    }

    /**
     * 动画被创建后回调
     *
     * @param show
     * @param view
     * @param animator
     */
    protected void onCreated(boolean show, View view, Animator animator)
    {
    }

    /**
     * 创建动画
     *
     * @param show
     * @param view
     * @return
     */
    protected abstract Animator onCreateAnimator(boolean show, View view);

    /**
     * 动画开始
     *
     * @param show
     * @param view
     */
    protected abstract void onAnimationStart(boolean show, View view);

    /**
     * 动画结束
     *
     * @param show
     * @param view
     */
    protected abstract void onAnimationEnd(boolean show, View view);

    /**
     * 计算动画时长
     *
     * @param startValue  开始值
     * @param endValue    结束值
     * @param maxValue    最大值
     * @param maxDuration 最大动画时长
     * @return
     */
    protected static long getScaledDuration(float startValue, float endValue, float maxValue, long maxDuration)
    {
        if (maxDuration <= 0)
            return 0;

        if (maxValue == 0)
            return 0;

        final float delta = startValue - endValue;
        if (delta == 0)
            return 0;

        final float percent = Math.abs(delta / maxValue);
        long duration = (long) (percent * maxDuration);
        if (duration > maxDuration)
            return maxDuration;

        return duration;
    }
}
