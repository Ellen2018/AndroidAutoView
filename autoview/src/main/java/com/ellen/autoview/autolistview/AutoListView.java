package com.ellen.autoview.autolistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
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

    private int centerY = 0;
    private int scrollY = 0;

    public void setAutoAdapter(AutoAdapter autoAdapter) {
        if (this.autoAdapter != null) {
            this.removeAllViews();
        }
        this.autoAdapter = autoAdapter;
        for (int i = 0; i < autoAdapter.itemCount(); i++) {
            View view = autoAdapter.getView(getContext(), i);
            //需要解决子View的测量问题
            if (viewList == null) {
                viewList = new ArrayList<>();
            }
            this.addView(view, i);
            viewList.add(view);
        }
        //开始绘制
        requestLayout();
        invalidate();
    }

    public AutoListView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
       this.setClickable(true);
    }

    public AutoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutoListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //处理宽
        int fatherWidth = MeasureSpec.getSize(widthMeasureSpec);
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(fatherWidth, MeasureSpec.AT_MOST);

        //获取到自身宽高
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int xmlWidth = layoutParams.width;
        int xmlHeight = layoutParams.height;

        int lastWidth = fatherWidth, lastHeight = 0;

        //处理高
        int fatherHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int fatherHeight = MeasureSpec.getSize(heightMeasureSpec);
        switch (fatherHeightMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                if (xmlHeight == LayoutParams.WRAP_CONTENT) {
                    lastHeight = fatherHeight;
                } else if (xmlHeight == LayoutParams.MATCH_PARENT) {
                    lastHeight = fatherHeight;
                } else {
                    lastHeight = xmlHeight;
                }
                break;
            case MeasureSpec.UNSPECIFIED:
                if (xmlHeight == LayoutParams.WRAP_CONTENT) {
                    lastHeight = 500;
                } else if (xmlHeight == LayoutParams.MATCH_PARENT) {
                    lastHeight = 500;
                } else {
                    lastHeight = xmlHeight;
                }
                break;
        }

        //测量子View
        for (int i = 0; i < viewList.size(); i++) {
            measureChild(viewList.get(i), childWidthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        }
        setMeasuredDimension(lastWidth, lastHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int left=0, top=0, right, bottom;

        for (int i = 0; i < viewList.size(); i++) {
            View view = viewList.get(i);
           if(i == 0){
               top = 0;
           }else {
               top += viewList.get(i-1).getMeasuredHeight();
           }
           right = left + view.getMeasuredWidth();
           bottom = top + view.getMeasuredHeight();
           view.layout(left,top,right,bottom);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //获取到系统最小滑动距离
//        int touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
//        //是否拦截
//        Log.e("Ellen2018","事件模式:"+ev.getAction());
//        boolean isIntercept = false;
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                centerY = (int) ev.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //并没有触发这里，写了有何用
//                Log.e("Ellen2018","触发滑动事件:"+touchSlop);
//                float rawY = ev.getRawY();
//                if(Math.abs(rawY - centerY) > touchSlop){
//                    //进行滑动
//                    Log.e("Ellen2018","进行滑动没有");
//                    isIntercept = true;
//                }
//                break;
//            case  MotionEvent.ACTION_UP:
//                centerY = 0;
//                break;
//        }

        Log.e("Ellen2018","是否拦截："+true);

        return true;
    }

    @Override
    public void scrollBy(int x, int y) {
        scrollY += y;
        if(scrollY > 0){
            //向下滑
            scrollTo(x,scrollY);
        }
        super.scrollBy(x, y);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("Ellen2018","是否处理事件："+true);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                centerY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("Ellen2018","是否处理MOVE事件："+true);
                float rawY = event.getRawY();
                int scrollY = (int) (centerY - rawY);
                centerY = (int) rawY;
                scrollBy(0, scrollY);
                break;
            case MotionEvent.ACTION_UP:
                centerY = 0;
                break;
        }

        return super.onTouchEvent(event);
    }
}
