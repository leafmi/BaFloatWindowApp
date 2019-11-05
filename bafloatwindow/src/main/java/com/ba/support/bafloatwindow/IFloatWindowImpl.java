package com.ba.support.bafloatwindow;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by jlang on 2019/3/2.
 */

public class IFloatWindowImpl extends IFloatWindow {

    private BaFloatWindow.Build mBuild;
    private FloatView mFloatView;
    private float downX;
    private float downY;
    private float upX;
    private float upY;
    private int mSlop;
    private boolean mClick = false;

    private boolean isShow;

    IFloatWindowImpl(BaFloatWindow.Build build) {
        this.mBuild = build;
        mFloatView = new FloatView(this.mBuild.mApplicationContext);
        if (this.mBuild.boolMove)
            initTouchEvent();
        mFloatView.setView(this.mBuild.mView);
        mFloatView.setSize(this.mBuild.mWidth, this.mBuild.mHeight);
        mFloatView.setGravity(this.mBuild.mGravity);
        mFloatView.setOffset(this.mBuild.xOffset, this.mBuild.yOffset);
        mFloatView.setAnimation(this.mBuild.animation);
    }

    @Override
    public void show() {
        if (!isShow) {
            isShow = true;
            mFloatView.show();
            if (mBuild.baFloatWindowListener != null)
                mBuild.baFloatWindowListener.onShow();
        }
    }

    @Override
    public void dismiss() {
        if (isShow) {
            isShow = false;
            mFloatView.dismiss();
            if (mBuild.baFloatWindowListener != null)
                mBuild.baFloatWindowListener.onDismiss();
        }
    }

    @Override
    public boolean isShowing() {
        return isShow;
    }

    @Override
    public View getView() {
        if (mBuild != null)
            return mBuild.mView;
        return null;
    }

    private void initTouchEvent() {
        View view = getView();
        if (view == null) return;
        mSlop = ViewConfiguration.get(mBuild.mApplicationContext).getScaledTouchSlop();
        view.setOnTouchListener(new View.OnTouchListener() {
            float lastX, lastY, changeX, changeY;
            int newX, newY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getRawX();
                        downY = event.getRawY();
                        lastX = event.getRawX();
                        lastY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        changeX = event.getRawX() - lastX;
                        changeY = event.getRawY() - lastY;
                        newX = (int) (mFloatView.getX() + changeX);
                        newY = (int) (mFloatView.getY() + changeY);
                        mFloatView.updateXY(newX, newY);
                        if (mBuild.baFloatWindowListener != null)
                            mBuild.baFloatWindowListener.onPositionUpdate(newX, newY);
                        lastX = event.getRawX();
                        lastY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        upX = event.getRawX();
                        upY = event.getRawY();
                        mClick = (Math.abs(upX - downX) > mSlop) || (Math.abs(upY - downY) > mSlop);
                        break;
                }
                return mClick;
            }
        });
    }
}
