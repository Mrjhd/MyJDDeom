package com.daydayup.myjddemo.view.view;

import android.content.Context;

import com.daydayup.myjddemo.model.Jiugongge_bean;
import com.daydayup.myjddemo.model.Shouye_bean;

import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/19.
 */

public  interface Iview {
    void setadapter(Context context, Shouye_bean shouye_bean);

    void setadapter1( List<Jiugongge_bean.DataBean> data);
}
