package com.ba.support.bafloatwindow;

import android.content.Context;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import android.util.SparseArray;
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

        //点击事件
        SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();

        int animation = android.R.style.Animation_Dialog;

        boolean boolMove = false;
        boolean outsideCanCancel = false;
        BaFloatWindowListener baFloatWindowListener;

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

        public Build setWidth(@Screen.screenType int screenType, float ratio) {
            mWidth = (int) ((screenType == Screen.width ?
                    Util.getScreenWidth(mApplicationContext) :
                    Util.getScreenHeight(mApplicationContext)) * ratio);
            return this;
        }

        public Build setHeight(@Screen.screenType int screenType, float ratio) {
            mHeight = (int) ((screenType == Screen.width ?
                    Util.getScreenWidth(mApplicationContext) :
                    Util.getScreenHeight(mApplicationContext)) * ratio);
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

        public Build setOnclickListener(int view, View.OnClickListener listener) {
            mClickArray.put(view, listener);
            return this;
        }

        public Build setOffset(int xOffset, int yOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            return this;
        }

        public Build setAnimation(int animation) {
            this.animation = animation;
            return this;
        }

        public Build setMove(boolean move) {
            this.boolMove = move;
            return this;
        }

        public Build setBaFloatWindowListener(BaFloatWindowListener baFloatWindowListener) {
            this.baFloatWindowListener = baFloatWindowListener;
            return this;
        }

        public Build setOutsideCanCancel(Boolean outsideCanCancel) {
            this.outsideCanCancel = outsideCanCancel;
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


    public interface BaFloatWindowListener {
        void onPositionUpdate(int x, int y);

        void onShow();

        void onDismiss();
    }
}
