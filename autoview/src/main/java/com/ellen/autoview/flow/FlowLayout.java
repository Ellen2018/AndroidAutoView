package com.ellen.autoview.flow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 流布局
 * <p>
 * 应用于搜素记录等
 */
public class FlowLayout extends ViewGroup {

    private List<List<View>> viewLineList = new ArrayList<>();
    private List<Integer> heightLineList = new ArrayList<>();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewLineList.clear();
        heightLineList.clear();
        //获取父View传递过来的测量模式 & size
        int fatherWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int fatherWidth = MeasureSpec.getSize(widthMeasureSpec);
        int fatherHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int fatherHeight = MeasureSpec.getSize(heightMeasureSpec);

        LayoutParams layoutParams = getLayoutParams();
        int xmlWidth = layoutParams.width;
        int xmlHeight = layoutParams.height;

        int lastWidth = 0,lastHeight = 0;

        //宽度
        if(xmlWidth == LayoutParams.WRAP_CONTENT){
            lastWidth = fatherWidth;
        }else if(xmlWidth == LayoutParams.MATCH_PARENT){
            lastWidth = fatherWidth;
        }else {
            lastWidth = xmlWidth;
        }

        boolean isMeasureHeight = false;
        //高度
        if(xmlHeight == LayoutParams.WRAP_CONTENT){
            isMeasureHeight = true;
        }else if(xmlHeight == LayoutParams.MATCH_PARENT){
            lastHeight = fatherHeight;
        }else {
            lastHeight = xmlHeight;
        }


        //遍历子View
        int childViewCount = getChildCount();
        //记录当前的使用宽度，使用完了之后就进行换行
        int currentUseWidth = 0;
        //记录当前行的最大高度
        int currentLineMaxHeight = 0;
        //记录当前行的占用的子View
        List<View> currentLineViews = new ArrayList<>();
        boolean isManyLine = false;
        for (int i = 0; i < childViewCount; i++) {
            View childView = getChildAt(i);
            //子View测量自己
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(lastWidth,MeasureSpec.AT_MOST);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(fatherHeight,MeasureSpec.AT_MOST);
            //measureChild为测量子View
            measureChild(childView, childWidthMeasureSpec, childHeightMeasureSpec);

            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();

            //获取子View最终占用的宽高
            int childLastWidth = childView.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            int childLastHeight = childView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;

            if(childLastWidth + currentUseWidth > lastWidth){
                //换行
                isManyLine = true;
                viewLineList.add(currentLineViews);
                heightLineList.add(currentLineMaxHeight);

                if(isMeasureHeight){
                    lastHeight += currentLineMaxHeight;
                }

                currentUseWidth = childLastWidth;
                currentLineMaxHeight = childLastHeight;
                currentLineViews = new ArrayList<>();
            }else {
                currentLineMaxHeight = Math.max(currentLineMaxHeight, childLastHeight);
                currentUseWidth += childLastWidth;
            }
            currentLineViews.add(childView);

            //处理最后一行
            if(i == getChildCount() - 1){
                if(isMeasureHeight){
                    lastHeight += currentLineMaxHeight;
                }

                viewLineList.add(currentLineViews);
                heightLineList.add(currentLineMaxHeight);
            }
        }

        if(!isManyLine){
            viewLineList.add(currentLineViews);
            heightLineList.add(currentLineMaxHeight);

            if(isMeasureHeight){
                lastHeight += currentLineMaxHeight;
            }
        }

        setMeasuredDimension(lastWidth,lastHeight);
    }

    /**
     * 布局过程
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //开始布局
        //1.取得所有视图信息
        //与之当前组件上下左右四个边距
        int left,top,right,bottom;
        //当前顶部高度和左部高度
        int curTop = 0;
        int curLeft = 0;
        //开始迭代
        int lineCount = heightLineList.size();
        for(int i = 0 ; i < lineCount ; i++) {
            List<View> viewList = viewLineList.get(i);
            int lineViewSize = viewList.size();
            for(int j = 0; j < lineViewSize; j++){
                View childView = viewList.get(j);
                MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();


                left = curLeft + layoutParams.leftMargin;
                top = curTop + layoutParams.topMargin;
                right = left + childView.getMeasuredWidth();
                bottom = top + childView.getMeasuredHeight();
                //同理，通过调用自身的layout进行布局
                childView.layout(left,top,right,bottom);
                //左边部分累加
                curLeft += childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            }
            curLeft = 0;
            curTop += heightLineList.get(i);
        }
        viewLineList.clear();
        heightLineList.clear();
    }

}
