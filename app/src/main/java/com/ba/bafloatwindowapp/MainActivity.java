package com.ba.bafloatwindowapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.ba.support.bafloatwindow.BaFloatWindow;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        findViewById(R.id.btn_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private BaFloatWindow mFloatWindow;

    private void show() {
        if (mFloatWindow == null)
            mFloatWindow = new BaFloatWindow.Build(this)
                    .setView(R.layout.layout_floatwindow)
                    .setGravity(Gravity.CENTER)
                    .setAnimation(R.style.floatwindow_animation)
                    .setOffset(0, 0)
                    .setMove(true)
                    .setOnclickListener(R.id.tv_click, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("TAG_TE", "点击事件111");
                        }
                    })
                    .setBaFloatWindowListener(new BaFloatWindow.BaFloatWindowListener() {
                        @Override
                        public void onPositionUpdate(int x, int y) {
                            Log.d("TAG_TE", "onPositionUpdate: " + x + "-" + y);
                        }

                        @Override
                        public void onShow() {
                        }

                        @Override
                        public void onDismiss() {
                        }
                    })
                    .build();
        mFloatWindow.show();
    }

    private void dismiss() {
        if (mFloatWindow != null)
            mFloatWindow.dismiss();
    }
}
