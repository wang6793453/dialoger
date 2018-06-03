package com.fanwe.lib.dialoger.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;

import com.fanwe.lib.dialoger.Dialoger;

public abstract class CombineCreater implements Dialoger.AnimatorCreater
{
    private Animator getAnimator(boolean show, View view, Dialoger.AnimatorCreater[] creaters)
    {
        if (creaters == null || creaters.length <= 0)
            return null;

        final AnimatorSet animatorSet = new AnimatorSet();

        Animator mLast = null;
        for (int i = 0; i < creaters.length; i++)
        {
            final Dialoger.AnimatorCreater item = creaters[i];
            if (item == null)
                continue;

            final Animator animator = item.createAnimator(show, view);
            if (animator == null)
                continue;

            if (mLast == null)
                animatorSet.play(animator);
            else
                animatorSet.play(mLast).with(animator);

            mLast = animator;
        }

        if (mLast == null)
            return null;

        return animatorSet;
    }

    @Override
    public final Animator createAnimator(boolean show, View view)
    {
        final Animator animator = getAnimator(show, view, getCreaters());
        return animator == null ? null : animator;
    }

    protected abstract Dialoger.AnimatorCreater[] getCreaters();
}
