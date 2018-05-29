package com.daydayup.myjddemo.view.view;

import com.daydayup.myjddemo.model.AddCartBean;

/**
 * Created by 因为有个你i on 2018/5/28.
 */

public interface AddCartViewCallBack {
    void success(AddCartBean addCartBean);

    void failure();
}
