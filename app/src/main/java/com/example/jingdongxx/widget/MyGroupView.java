package com.example.jingdongxx.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 姓名：
 * 日期：
 * 类作用：
 */

public class MyGroupView extends GridView {

    public MyGroupView(Context context) {
        this(context, null);
    }

    public MyGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
