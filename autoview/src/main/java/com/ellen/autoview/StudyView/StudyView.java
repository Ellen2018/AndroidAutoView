package com.ellen.autoview.StudyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class StudyView extends View {

    private com.ellen.autoview.StudyView.MeasureSpec measureSpec;

    public void setMeasureSpec(com.ellen.autoview.StudyView.MeasureSpec measureSpec) {
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
        }
    }
}
