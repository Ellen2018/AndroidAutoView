package com.ellen.androidautoview;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.ellen.androidautoview.base.BaseActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * CoordinatorLayout玩法
 */
public class AppBarLayoutActivity extends BaseActivity {

    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_app_bar_layout;
    }

    @Override
    protected void setStatus() {

    }

    @Override
    protected void initView() {
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        appBarLayout = findViewById(R.id.appbar_layout);
        toolbar = findViewById(R.id.appbar_layout_toolbar);
        collapsingToolbarLayout.setContentScrimColor(Color.RED);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(-i>10){
                    Log.e("Ellen2018","触发事件");
                }
            }
        });
    }

    @Override
    protected void initData() {

    }
}
