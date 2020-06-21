package com.ellen.androidautoview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ellen.autoview.autolistview.AutoAdapter;
import com.ellen.autoview.autolistview.AutoListView;
import com.ellen.autoview.studyView.StudyView;

public class MainActivity extends AppCompatActivity {

    private AutoListView autoListView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoListView = findViewById(R.id.auto_list_view);
        autoListView.setAutoAdapter(new AutoAdapter() {
            @Override
            public int itemCount() {
                return 100;
            }

            @Override
            public View getView(Context context, int position) {
                TextView textView = new TextView(MainActivity.this);
                textView.setText(String.valueOf(position));
                textView.setClickable(true);
                textView.setTextSize(120);
                return textView;
            }
        });
    }
}