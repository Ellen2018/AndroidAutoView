package com.ellen.autoview.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasView extends View {

    private int mWidth, mHeight;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, paint);

        //画刻度线
        paint.reset();
        paint.setStrokeWidth(3);
        for (int i = 0; i < 12; i++) {
            if (i % 3 == 0) {
                //画长线
                paint.setStrokeWidth(5);
                paint.setTextSize(100);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 60, paint);
                String hourString = null;

                if (i == 0) {
                    //画0
                    hourString = "12";
                    canvas.drawText(hourString, mWidth / 2 - paint.measureText(hourString) / 2, mHeight / 2 - mWidth / 2 + 140, paint);
                    hourString = "3";
                    canvas.drawText(hourString, mWidth - 130, mHeight / 2 + paint.measureText(hourString), paint);
                    hourString = "6";
                    canvas.drawText(hourString, mWidth / 2 - paint.measureText(hourString) / 2, mHeight / 2 + mWidth / 2 - 70, paint);
                    hourString = "9";
                    canvas.drawText(hourString, 0+70, mHeight / 2 + paint.measureText(hourString), paint);
                }

            } else {
                //画短线
                paint.setStrokeWidth(3);
                paint.setTextSize(50);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 40, paint);
                String hourString = null;
                if (i != 0) {
                    hourString = String.valueOf(i);
                } else {
                    hourString = "12";
                }
                canvas.drawText(hourString, mWidth / 2 - paint.measureText(hourString) / 2, mHeight / 2 - mWidth / 2 + 80, paint);
            }
            canvas.rotate(30, mWidth / 2, mHeight / 2);
        }

        //画指针

        //画时针
        paint.reset();
        paint.setStrokeWidth(20);
        //平移下笔点到中心位置
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0, 0, 100, 100, paint);

        //画分针
        paint.reset();
        paint.setStrokeWidth(10);
        canvas.drawLine(0, 0, 100, 200, paint);
    }
}
