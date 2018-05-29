package com.daydayup.myjddemo.model;

import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public interface OnRequestListener {
    void OnSuccess(List<FenleiLeftBean.DataBean> list);
    void OnRightSuccess(List<FenleiRightBean.DataBean> list);
    void OnError(String e);
}
