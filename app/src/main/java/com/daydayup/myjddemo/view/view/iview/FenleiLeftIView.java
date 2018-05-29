package com.daydayup.myjddemo.view.view.iview;

import com.daydayup.myjddemo.model.FenleiLeftBean;

import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public interface FenleiLeftIView {
    void showList(List<FenleiLeftBean.DataBean> list);
    void showError(String e);
}
