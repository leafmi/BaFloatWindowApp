package com.ba.support.bafloatwindow;

/**
 * Created by jlang on 2019/3/2.
 */

public abstract class IBaFloatWindow {
    IFloatWindow floatWindow;

    public void show() {
        if (floatWindow != null)
            floatWindow.show();
    }

    public void dismiss() {
        if (floatWindow != null)
            floatWindow.dismiss();
    }

    public boolean isShowing() {
        if (floatWindow != null)
            return floatWindow.isShowing();
        return false;
    }
}
