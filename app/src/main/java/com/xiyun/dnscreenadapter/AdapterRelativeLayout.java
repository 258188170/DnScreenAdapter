package com.xiyun.dnscreenadapter;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by WangPeng on 2020-05-02.
 */
public class AdapterRelativeLayout extends RelativeLayout {
    private static final String TAG = "AdapterRelativeLayout";
    static boolean isFlag = true;

    public AdapterRelativeLayout(Context context) {
        super(context);
    }

    public AdapterRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdapterRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isFlag) {
            int childCount = this.getChildCount();
            float scaleX =  UIUtils.getInstance(getContext()).getHorizontalScaleValue();
            float scaleY =  UIUtils.getInstance(getContext()).getVerticalScaleValue();


            Log.i(TAG, "onMeasure: x--" + scaleX + "y--" + scaleY);

            for (int i = 0; i < childCount; i++) {
                View view = this.getChildAt(i);
                //当前素有控件的属性
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

                //从新设置属性
                layoutParams.width = (int) (layoutParams.width * scaleX);
                layoutParams.height = (int) (layoutParams.height * scaleY);
                layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
                layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
            }
            isFlag = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
