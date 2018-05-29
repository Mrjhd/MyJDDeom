package com.daydayup.myjddemo.view.view.iview;

import com.daydayup.myjddemo.model.FenleiLiebiaoBean;

import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public interface LieBiaoIView {
    void showList(List<FenleiLiebiaoBean.DataBean> list);
    void showError(String e);
}
