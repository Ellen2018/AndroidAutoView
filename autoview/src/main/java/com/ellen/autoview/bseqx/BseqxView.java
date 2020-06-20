package com.ellen.autoview.bseqx;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 贝塞尔曲线
 *
 * Path 能完成 二阶贝塞尔曲线 & 三阶贝塞尔曲线
 *
 * 其它高阶使用公式自己封装
 *
 */
public class BseqxView extends View {

    public BseqxView(Context context) {
        super(context);
    }

    public BseqxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BseqxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
