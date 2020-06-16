package com.ellen.autoview.autolistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义ListView
 * 难点:
 * 1.如何显示多个Item
 * 2.如何支持滑动
 */
public class AutoListView extends ViewGroup {

    /**
     * 适配器 直接使用原生的
     */
    private AutoAdapter autoAdapter;
    /**
     * Item集合
     */
    private List<View> viewList;
    private int y;
    /**
     * 总行数
     */
    private int row;

    public void setAutoAdapter(AutoAdapter autoAdapter) {
        this.autoAdapter = autoAdapter;
        //开始绘制
        requestLayout();
        invalidate();
    }

    public AutoListView(Context context) {
        super(context);
        init(context,null);
    }

    private void init(Context context,AttributeSet attrs) {
 
    }

    public AutoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public AutoListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < autoAdapter.itemCount(); i++) {
            View view = autoAdapter.getView(getContext(), i);
            //需要解决子View的测量问题
            view.measure(widthMeasureSpec, heightMeasureSpec);
            if (viewList == null) {
                viewList = new ArrayList<>();
            }
            Log.e("Elle2018", "测量之后View的高度:" + view.getMeasuredHeight());
            viewList.add(view);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < autoAdapter.itemCount(); i++) {
            View view = viewList.get(i);

            if (view.getMeasuredHeight() == 0) {
                view.layout(l, t + i * 100, r, t + i * 100 + 100);
            } else {
                int h = view.getMeasuredWidth();
                int left = l;
                int top = t + (i - 1) * h;
                int right = r;
                int bottom = top + h;
                view.layout(left, top, right, bottom);
            }

            //进行布局
            this.addView(view);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
