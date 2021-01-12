package com.ba.support.bafloatwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;

import com.ba.support.bafloatwindow.util.PermissionUtil;

import java.lang.ref.WeakReference;


/**
 * Created by jlang on 2019/3/2.
 */

class FloatView {

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    private View mView;
    private int mX = -1, mY = -1;
    private boolean isRemove = false;

    private int mScreenHeight, mScreenWidth;
    private SparseArray<WeakReference<View>> mIdViews = new SparseArray<>();

    FloatView(Context context) {
        mScreenHeight = Util.getScreenHeight(context);
        mScreenWidth = Util.getScreenWidth(context);
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
                    | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;//允许窗口延伸到屏幕外。
        }
    }


    private <T extends View> T getView(int viewId) {
        WeakReference<View> viewReference = mIdViews.get(viewId);
        View view = null;
        if (viewReference != null) {
            view = mIdViews.get(viewId).get();
        }
        if (view == null) {
            view = mView.findViewById(viewId);
            if (view != null) {
                mIdViews.put(viewId, new WeakReference<>(view));
            }
        }
        return (T) view;
    }

    void setView(View view) {
        this.mView = view;
    }

    public void setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    public void addLayoutParamsFlags(int flags) {
        if (mLayoutParams != null) {
            int lodFlags = mLayoutParams.flags;
            mLayoutParams.flags = lodFlags | flags;
        }
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
        mLayoutParams.x = mX = x;
        mLayoutParams.y = mY = y;
    }


    void setAnimation(int animation) {
        if (mLayoutParams == null) return;
        mLayoutParams.windowAnimations = animation;
    }

    void show() {
        isRemove = false;
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


    void dismiss() {
        isRemove = true;
        try {
            if (mWindowManager != null && mView != null)
                mWindowManager.removeView(mView);
//            mWindowManager.removeViewImmediate(mView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateXY(int x, int y) {
        if (isRemove) return;
        mLayoutParams.x = mX = x;
        mLayoutParams.y = mY = y;
        mWindowManager.updateViewLayout(mView, mLayoutParams);
    }

    void updateX(int x) {
        if (isRemove) return;
        mLayoutParams.x = mX = x;
        mWindowManager.updateViewLayout(mView, mLayoutParams);
    }

    void updateY(int y) {
        if (isRemove) return;
        mLayoutParams.y = mY = y;
        mWindowManager.updateViewLayout(mView, mLayoutParams);
    }

    int getX() {
        return mX;
    }

    int getY() {
        return mY;
    }
}
