package com.ba.support.bafloatwindow;

import android.view.View;

/**
 * Created by jlang on 2019/3/2.
 */

public class IFloatWindowImpl extends IFloatWindow {

    private BaFloatWindow.Build mBuild;
    private FloatView mFloatView;


    private boolean isShow;

    IFloatWindowImpl(BaFloatWindow.Build build) {
        this.mBuild = build;
        mFloatView = new FloatView(this.mBuild.mApplicationContext);
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
        }
    }

    @Override
    public void dismiss() {
        if (isShow) {
            isShow = false;
            mFloatView.dismiss();
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
}
