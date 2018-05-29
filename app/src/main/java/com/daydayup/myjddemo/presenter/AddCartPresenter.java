package com.daydayup.myjddemo.presenter;

import com.daydayup.myjddemo.model.AddCartBean;
import com.daydayup.myjddemo.model.AddCartModel;
import com.daydayup.myjddemo.model.AddCartModelCallBack;
import com.daydayup.myjddemo.view.view.AddCartViewCallBack;

/**
 * Created by 因为有个你i on 2018/5/28.
 */

public class AddCartPresenter {
    AddCartModel addCartModel = new AddCartModel();

    AddCartViewCallBack addCartViewCallBack;
    public AddCartPresenter(AddCartViewCallBack addCartViewCallBack) {
        this.addCartViewCallBack = addCartViewCallBack;
    }

    public void getData(String pid) {

        addCartModel.getData(pid, new AddCartModelCallBack() {
            @Override
            public void success(AddCartBean addCartBean) {
                addCartViewCallBack.success(addCartBean);
            }

            @Override
            public void failure() {
                addCartViewCallBack.failure();
            }
        });

    }
}
