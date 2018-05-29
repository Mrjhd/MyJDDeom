package com.daydayup.myjddemo.view.view.iview;

import com.daydayup.myjddemo.model.CartBean;

/**
 * Created by 因为有个你i on 2018/5/25.
 */

public interface MyViewCallBack {
    public void success(CartBean cartBean);
    public void failure();
}
