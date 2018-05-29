package com.daydayup.myjddemo.model;

import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public interface OnLieBiaoListener {
    void OnRightSuccess(List<FenleiLiebiaoBean.DataBean> list);
    void OnError(String e);
}
