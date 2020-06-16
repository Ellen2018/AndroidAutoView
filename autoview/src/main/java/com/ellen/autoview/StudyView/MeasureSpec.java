package com.ellen.autoview.StudyView;

import android.view.ViewGroup;

public interface MeasureSpec {
    void getWidthMeasureSpec(SpecMode widthMode,int width);
    void getHeightMeasureSpec(SpecMode heightMode,int height);
}
