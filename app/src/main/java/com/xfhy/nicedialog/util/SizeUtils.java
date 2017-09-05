package com.xfhy.nicedialog.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * author feiyang
 * create at 2017/9/5 17:13
 * description： 屏幕大小,dp,px等操作的工具类
 */
public class SizeUtils {

    /**
     * dp转px
     *
     * @param context  Context
     * @param dipValue dp值
     * @return 返回dp转px的值
     */
    public static int dp2px(Context context, float dipValue) {
        //返回该设备上的1个dp  逻辑密度
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (scale * dipValue + 0.5f);
    }

    /**
     * 获取屏幕宽度
     * @param context Context
     * @return 返回屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context
                .WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

}
