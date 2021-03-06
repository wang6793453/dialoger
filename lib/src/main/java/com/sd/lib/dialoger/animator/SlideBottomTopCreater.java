package com.sd.lib.dialoger.animator;

import android.view.View;

/**
 * 向下滑入，向上滑出
 */
public class SlideBottomTopCreater extends SlideVerticalCreater
{
    private float mValueOriginal;

    @Override
    protected void beforeCreateAnimator(boolean show, View view)
    {
        super.beforeCreateAnimator(show, view);
        if (show)
            mValueOriginal = view.getTranslationY();
    }

    @Override
    protected float getValueHidden(View view)
    {
        return -view.getHeight();
    }

    @Override
    protected float getValueShown(View view)
    {
        return mValueOriginal;
    }

    @Override
    protected void onAnimationEnd(boolean show, View view)
    {
        super.onAnimationEnd(show, view);
        if (!show)
            view.setTranslationY(mValueOriginal);
    }
}
