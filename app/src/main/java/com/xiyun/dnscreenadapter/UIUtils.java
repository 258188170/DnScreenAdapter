package com.xiyun.dnscreenadapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by WangPeng on 2020-05-02.
 */
public class UIUtils {
    private static final String TAG = "UIUtils";
    public static final String DEMIN_CLASS = "com.android.internal.R$dimen";
    private static volatile UIUtils mUIUtils;
    //基准参照宽高
    public final float STANDARD_WIDTH = 1440;
    public final float STANDARD_HEIGHT = 2344;

    //当前设备实际宽高
    public float displayMetricsWidth;
    public float displayMetricsHeight;
    private Context mContext;

    private UIUtils(Context context) {
        this.mContext = context;
        init();


    }

    public static UIUtils getInstance(Context context) {
        if (mUIUtils == null) {
            synchronized (UIUtils.class) {
                if (mUIUtils == null) {
                    return new UIUtils(context.getApplicationContext());

                }
            }
        }
        return mUIUtils;
    }

    public float getHorizontalScaleValue() {
        return displayMetricsWidth / STANDARD_WIDTH;
    }

    public float getVerticalScaleValue() {
        return displayMetricsHeight / STANDARD_HEIGHT;
    }

    /**
     * 初始化/获取当前屏幕宽高
     */
    private void init() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        //加载当前屏幕信息
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        //初始化一次
        if (displayMetricsWidth == 0.0f || displayMetricsHeight == 0.0f) {
            int systemBarHeight = getValue(mContext, "system_bar_height", 48);
            //横竖屏适配宽高处理
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                this.displayMetricsWidth = displayMetrics.heightPixels;
                this.displayMetricsHeight = displayMetrics.widthPixels - systemBarHeight;
            } else {
                this.displayMetricsWidth = displayMetrics.widthPixels;
                this.displayMetricsHeight = displayMetrics.heightPixels - systemBarHeight;

            }
            Log.i(TAG, "init: 当前宽-->"+displayMetricsWidth+"高-->>"+displayMetricsHeight);
        }

    }

    private int getValue(Context context, String systemId, int defValue) {

        try {
            Class<?> clazz = Class.forName(DEMIN_CLASS);
            Object r = clazz.newInstance();

            Field field = clazz.getField(systemId);
            int x = (int) field.get(r);

            int dimensionPixelOffset = context.getResources()
                    .getDimensionPixelOffset(x);
            return dimensionPixelOffset;

        } catch (Exception e) {
            return defValue;
        }


    }


}
