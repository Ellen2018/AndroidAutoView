package com.ellen.androidautoview.base;

import android.view.View;

/**
 * 用于依赖注入框架引入
 */
public interface BaseRegister {
    void register(View contentView);
    void unRegister(View contentView);
}
