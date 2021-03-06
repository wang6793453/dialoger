package com.sd.lib.dialoger.animator;

import android.view.View;

/**
 * 竖直方向滑动
 */
public abstract class SlideVerticalCreater extends ObjectAnimatorCreater
{
    @Override
    protected final String getPropertyName()
    {
        return View.TRANSLATION_Y.getName();
    }

    @Override
    protected final float getValueCurrent(View view)
    {
        return view.getTranslationY();
    }
}
