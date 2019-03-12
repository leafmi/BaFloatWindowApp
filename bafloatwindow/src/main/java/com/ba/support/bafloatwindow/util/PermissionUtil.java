package com.ba.support.bafloatwindow.util;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;

import java.lang.reflect.Method;

/**
 * Created by jlang on 2019/3/2.
 */

public class PermissionUtil {

    private static final int OP_SYSTEM_ALERT_WINDOW = 24;

    public static boolean canDrawOverlays(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return checkOp(context, OP_SYSTEM_ALERT_WINDOW);
        } else {
            return true;
        }
    }

    private static boolean checkOp(Context context, int op) {
        try {

            AppOpsManager manager = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                Method method = AppOpsManager.class.getDeclaredMethod("checkOp", int.class, int.class, String.class);
                return AppOpsManager.MODE_ALLOWED == (int) method.invoke(manager, op, Binder.getCallingUid(), context.getPackageName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
