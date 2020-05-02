package com.xiyun.dnscreenadapter;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by WangPeng on 2020-05-02.
 */
public class ScreenAdapterRelativeLayout extends RelativeLayout {

    static boolean isFlag = true;

    public ScreenAdapterRelativeLayout(Context context) {
        super(context);
    }

    public ScreenAdapterRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenAdapterRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            if (isFlag){
                int childCount = this.getChildCount();

                for (int i = 0; i < childCount; i++) {
                    View view = this.getChildAt(i);
                    //当前素有控件的属性
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

                }

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
