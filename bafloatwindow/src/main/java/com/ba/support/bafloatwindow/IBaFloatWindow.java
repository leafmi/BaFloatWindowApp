package com.ba.support.bafloatwindow;

import android.view.View;

/**
 * Created by jlang on 2019/3/2.
 */

public class IBaFloatWindow {
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


    public View getView() {
        if (floatWindow != null)
            return floatWindow.getView();
        return null;
    }
}
