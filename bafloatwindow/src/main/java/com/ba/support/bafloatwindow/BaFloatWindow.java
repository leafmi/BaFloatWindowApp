package com.ba.support.bafloatwindow;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by jlang on 2019/3/2.
 */

public class BaFloatWindow extends IBaFloatWindow {


    private BaFloatWindow(Build build) {
        floatWindow = new IFloatWindowImpl(build);
    }

    public static class Build {
        Context mApplicationContext;
        View mView;
        private int mLayoutId;
        int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;

        int mGravity = Gravity.TOP | Gravity.START;
        int xOffset;
        int yOffset;

        public Build(Context mApplicationContext) {
            this.mApplicationContext = mApplicationContext;
        }

        public Build setView(@NonNull View view) {
            this.mView = view;
            return this;
        }

        public Build setView(@LayoutRes int layoutId) {
            this.mLayoutId = layoutId;
            return this;
        }

        public Build setWidth(int width) {
            mWidth = width;
            return this;
        }

        public Build setHeight(int height) {
            mHeight = height;
            return this;
        }

        public Build setGravity(int gravity) {
            this.mGravity = gravity;
            return this;
        }

        public Build setOffset(int xOffset, int yOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            return this;
        }

        public BaFloatWindow build() {
            if (mView == null && mLayoutId == 0) {
                throw new IllegalArgumentException("View has not been set!");
            }
            if (mView == null) {
                mView = View.inflate(mApplicationContext, mLayoutId, null);
            }
            return new BaFloatWindow(this);
        }
    }
}
