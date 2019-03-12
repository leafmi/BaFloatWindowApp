package com.ba.support.bafloatwindow;

import android.view.View;

/**
 * Created by jlang on 2019/3/2.
 */

public abstract class IFloatWindow {

    public abstract void show();

    public abstract void dismiss();

    public abstract boolean isShowing();

    public abstract View getView();

}
