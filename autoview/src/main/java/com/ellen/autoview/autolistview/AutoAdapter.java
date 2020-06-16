package com.ellen.autoview.autolistview;

import android.content.Context;
import android.view.View;

public interface AutoAdapter {

    /**
     * 显示的item总数
     * @return
     */
    int itemCount();

    /**
     * 返回itemview
     * @param context
     * @param position
     * @return
     */
    View getView(Context context,int position);

}
