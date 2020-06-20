package com.ellen.autoview.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 帧动画
 * 补间动画
 * 属性动画
 *   均匀变化
 *   不均匀变化
 *     估值器
 *     估值器
 *
 */
public class AnimationView extends View {
    public AnimationView(Context context) {
        super(context);
    }

    public AnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
