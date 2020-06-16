package com.ellen.autoview.studyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public class StudyView extends View {

    private com.ellen.autoview.studyView.MeasureSpec measureSpec;

    public void setMeasureSpec(com.ellen.autoview.studyView.MeasureSpec measureSpec) {
        this.measureSpec = measureSpec;
    }

    /**
     * 在代码中直接使用调用此构造方法
     * example:
     *
     * Study study = new Study();
     *
     * @param context
     */
    public StudyView(Context context) {
        super(context);
    }

    /**
     * 在布局文件中声明时调用此构造方法(不带style)
     * @param context
     * @param attrs 自定义属性
     */
    public StudyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 在布局文件中声明时调用此构造方法(带style)
     * @param context
     * @param attrs 自定义属性
     * @param defStyleAttr style id
     */
    public StudyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 测量自身
     * LayoutParams熟悉：https://www.jianshu.com/p/99c27e2db843
     *
     * LayoutParams.Match -> -1
     * LayoutParams.Wrap -> -2
     *
     * MeasureSpec：https://www.cnblogs.com/66it/p/10486047.html
     *
     * 它是要给int 32位的整数值
     * 高两位：测量模式
     * 低两位：测量size
     *
     * -------------------------------
     * 谨记：
     *
     * 对于一个自定义ViewGroup而言：
     *
     * 其实测量过程就是结合父View的MeasureSpec
     * 加上自身的LayoutParams计算出View自身的MeasureSpec
     * 然后将它交给子View测量自己,得到最终自身的宽高，最终调用
     * setMeasuredDimension设置最终的宽高
     *
     * 对于一个自定义View而言：
     *
     * 其实测量过程就是结合父View的MeasureSpec
     * 加上自身的LayoutParams计算出具体的宽高，最终调用
     * setMeasuredDimension设置最终的宽高
     * 
     * ---------------------------------
     *
     * @param widthMeasureSpec 父View的widthMeasureSpec
     * @param heightMeasureSpec 父View的heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(measureSpec != null){
            //获取父View传递过来的测量模式

            int intWidthMode = MeasureSpec.getMode(widthMeasureSpec);
            SpecMode widthMode = null;
            if(intWidthMode == MeasureSpec.AT_MOST){
                widthMode = SpecMode.AT_MOST;
            }else if(intWidthMode == MeasureSpec.EXACTLY){
                widthMode = SpecMode.EXACTLY;
            }else {
                widthMode = SpecMode.UNSPECIFIED;
            }

            int intHeightMode = MeasureSpec.getMode(heightMeasureSpec);
            SpecMode heightMode = null;
            if(intHeightMode == MeasureSpec.AT_MOST){
                heightMode = SpecMode.AT_MOST;
            }else if(intHeightMode == MeasureSpec.EXACTLY){
                heightMode = SpecMode.EXACTLY;
            }else {
                heightMode = SpecMode.UNSPECIFIED;
            }

            measureSpec.getWidthMeasureSpec(widthMode,MeasureSpec.getSize(widthMeasureSpec));
            measureSpec.getHeightMeasureSpec(heightMode,MeasureSpec.getSize(heightMeasureSpec));

            //忽略以上代码

            //获取LayoutParams
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            int width = layoutParams.width;
            int height = layoutParams.height;

            //最终的宽高记录
            int lastWidth = 0;
            int lastHeight = 0;

            //这里的逻辑写的过于啰嗦，但是对于初学者而言逻辑易于理解
            int fatherWidth = MeasureSpec.getSize(widthMeasureSpec);
            if(width == ViewGroup.LayoutParams.MATCH_PARENT){
                //宽度为match -> 匹配父控件大小
                if(intHeightMode == MeasureSpec.AT_MOST){
                    lastWidth = fatherWidth;
                }else if(intHeightMode == MeasureSpec.EXACTLY){
                    lastWidth = fatherWidth;
                }else {
                   lastWidth = fatherWidth;
                }
            }else if(width == ViewGroup.LayoutParams.WRAP_CONTENT){
                //宽度为warp -> 内容最小宽度
                //这里需要确定View自身的内容宽度->比如文本大小的宽度等
                //这里的逻辑笔者就不写了
            }else {
                //宽度为具体值
                if(intHeightMode == MeasureSpec.AT_MOST){
                    if(width > fatherWidth){
                        lastWidth = fatherWidth;
                    }else {
                        lastWidth = width;
                    }
                }else if(intHeightMode == MeasureSpec.EXACTLY){
                    if(width > fatherWidth){
                        lastWidth = fatherWidth;
                    }else {
                        lastWidth = width;
                    }
                }else {
                    lastWidth = width;
                }
            }

            //高度与宽度的计算逻辑一样

            //最终调用setMeasuredDimension确定宽高
            setMeasuredDimension(lastWidth,lastHeight);
        }
    }
}
