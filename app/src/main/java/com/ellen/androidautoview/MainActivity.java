package com.ellen.androidautoview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.ellen.autoview.studyView.StudyView;

public class MainActivity extends AppCompatActivity {

    private StudyView studyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studyView = findViewById(R.id.study_view);
    }
}