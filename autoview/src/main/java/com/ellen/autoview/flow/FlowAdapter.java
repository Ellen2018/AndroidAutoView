package com.ellen.autoview.flow;

import android.view.View;

public interface FlowAdapter {
    int itemSize();
    View createView(int position);
    void bindView(View view,int position);
}
