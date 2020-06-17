package com.ellen.autoview.studyView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public class StudyView extends View {

    /**
     * 在代码中直接使用调用此构造方法
     * example:
     * <p>
     * Study study = new Study();
     *
     * @param context
     */
    public StudyView(Context context) {
        super(context);
    }

    /**
     * 在布局文件中声明时调用此构造方法(不带style)
     *
     * @param context
     * @param attrs   自定义属性
     */
    public StudyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 在布局文件中声明时调用此构造方法(带style)
     *
     * @param context
     * @param attrs        自定义属性
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
     * @param widthMeasureSpec  父View的widthMeasureSpec
     * @param heightMeasureSpec 父View的heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        //获取父View传递过来的测量模式 & size
        int intWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int fatherWidth = MeasureSpec.getSize(widthMeasureSpec);
        int intHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int fatherHeight = MeasureSpec.getSize(heightMeasureSpec);

        //获取LayoutParams
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int width = layoutParams.width;
        int height = layoutParams.height;

        //最终的宽高记录
        int lastWidth = getLastSize(true,intWidthMode,fatherWidth,width);
        int lastHeight = getLastSize(false,intHeightMode,fatherHeight,height);

        //最终调用setMeasuredDimension确定宽高
        setMeasuredDimension(lastWidth, lastHeight);
    }

    /**
     * 这里的逻辑写的过于啰嗦，但是对于初学者而言逻辑易于理解
     * @param isWidth 是否为宽度
     * @param mode 父View的测量模式
     * @param fatherSize 父亲的大小
     * @param xmlSize 子View自身Xml中设置大小
     * @return
     */
    private int getLastSize(boolean isWidth,int mode,int fatherSize,int xmlSize){
        int lastSize = 0;
        switch (mode){
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                if(xmlSize == ViewGroup.LayoutParams.MATCH_PARENT){
                    lastSize = fatherSize;
                }else if(xmlSize == ViewGroup.LayoutParams.WRAP_CONTENT){
                    //这里需要测量StudyView自身的大小，然后确定最终的lastSize
                    int wrapSize = 100;//加入经过内容测量计算出内容size为100
                    if(wrapSize > fatherSize){
                        lastSize = fatherSize;
                    }else {
                        lastSize = wrapSize;
                    }
                }else {
                    if(fatherSize < xmlSize){
                        lastSize = fatherSize;
                    }else {
                        lastSize = xmlSize;
                    }
                }
                break;
            case MeasureSpec.UNSPECIFIED:
                //父亲测量模式为 MeasureSpec.UNSPECIFIED,fatherSize = 0
                if(xmlSize == ViewGroup.LayoutParams.MATCH_PARENT){
                    //这个地方，由于父View给的size = 0,我这里直接设置为200
                    lastSize = 200;
                }else if(xmlSize == ViewGroup.LayoutParams.WRAP_CONTENT){
                    int wrapSize = 500;//加入经过内容测量计算出内容size为500
                    lastSize = wrapSize;
                }else {
                    lastSize = xmlSize;
                }
                break;
        }
        return lastSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas为画布
    }
}
