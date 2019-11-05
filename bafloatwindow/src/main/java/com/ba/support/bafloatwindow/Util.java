package com.ba.support.bafloatwindow;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

/**
 * Created by jlang on 2019-11-05.
 */

class Util {

    private static Point sPoint;

    static int getScreenWidth(Context context) {
        if (sPoint == null) {
            sPoint = new Point();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (wm != null)
                wm.getDefaultDisplay().getSize(sPoint);
        }
        return sPoint.x;
    }

    static int getScreenHeight(Context context) {
        if (sPoint == null) {
            sPoint = new Point();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (wm != null)
                wm.getDefaultDisplay().getSize(sPoint);
        }
        return sPoint.y;
    }
}
