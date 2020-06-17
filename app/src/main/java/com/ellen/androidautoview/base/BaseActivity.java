package com.ellen.androidautoview.base;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 至于横竖屏设置，请在AndroidManifest.xml中进行配置
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatus();
        if(setLayoutId() == -1){
            rootView = setView();
            setContentView(rootView);
        }else {
            setContentView(setLayoutId());
        }
        if(this instanceof BaseRegister){
           BaseRegister baseRegister = (BaseRegister) this;
           baseRegister.register(rootView);
        }
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(this instanceof BaseRegister){
            BaseRegister baseRegister = (BaseRegister) this;
            baseRegister.unRegister(rootView);
        }
    }

    //设置状态栏
    protected abstract void setStatus();
    //设置布局id
    protected int setLayoutId(){
        return -1;
    }
    protected View setView(){
        return null;
    }
    protected abstract void initView();
    protected abstract void initData();

    protected boolean back(){
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(back()){
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
