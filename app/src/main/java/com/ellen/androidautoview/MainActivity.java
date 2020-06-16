package com.ellen.androidautoview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ellen.autoview.StudyView.MeasureSpec;
import com.ellen.autoview.StudyView.SpecMode;
import com.ellen.autoview.StudyView.StudyView;
import com.ellen.autoview.autolistview.AutoAdapter;
import com.ellen.autoview.autolistview.AutoListView;

public class MainActivity extends AppCompatActivity {

    private StudyView studyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studyView = findViewById(R.id.study_view);
        studyView.setMeasureSpec(new MeasureSpec() {
            @Override
            public void getWidthMeasureSpec(SpecMode widthMode, int width) {
                Log.e("Ellen2018","宽度测量模式:"+widthMode);
                Log.e("Ellen2018","宽度测量size:"+width);
            }

            @Override
            public void getHeightMeasureSpec(SpecMode heightMode, int height) {
                Log.e("Ellen2018","高度测量模式:"+heightMode);
                Log.e("Ellen2018","高度测量size:"+height);
            }
        });
        ViewGroup.LayoutParams layoutParams = studyView.getLayoutParams();
        Log.e("Ellen2018","宽度LayoutParams:"+layoutParams.width);
        Log.e("Ellen2018","高度LayoutParams:"+layoutParams.height);
    }
}