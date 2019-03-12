package com.ba.support.bafloatwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.ba.support.bafloatwindow.util.PermissionUtil;


/**
 * Created by jlang on 2019/3/2.
 */

public class FloatView {

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    private View mView;

    FloatView(Context context) {
        boolean drawOverlays;
        drawOverlays = PermissionUtil.canDrawOverlays(context);
        if (drawOverlays) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

            mLayoutParams = new WindowManager.LayoutParams();
            if (Build.VERSION.SDK_INT >= 19) {
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST; // 2005
            } else {
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE; // 2002
            }

            //6.0
            if (Build.VERSION.SDK_INT < 23) {
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
            }

            //8.0
            if (Build.VERSION.SDK_INT >= 26) {
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            }

            mLayoutParams.format = PixelFormat.RGBA_8888;
            mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE   //设置不阻挡其他view的触摸事件
                    | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
            mLayoutParams.windowAnimations = 0;
        }
    }


    public void setView(View view) {
        this.mView = view;
    }

    void setSize(int width, int height) {
        if (mLayoutParams == null) return;
        mLayoutParams.width = width;
        mLayoutParams.height = height;
    }

    void setGravity(int gravity) {
        if (mLayoutParams == null) return;
        mLayoutParams.gravity = gravity;
    }

    void setOffset(int x, int y) {
        if (mLayoutParams == null) return;
        mLayoutParams.x = x;
        mLayoutParams.y = y;
    }

    public void show() {
        try {
            if (mWindowManager != null
                    && mLayoutParams != null
                    && mView != null) {
                mWindowManager.addView(mView, mLayoutParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void dismiss() {
        try {
            if (mWindowManager != null && mView != null)
                mWindowManager.removeView(mView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
