package com.daydayup.myjddemo.presenter;

import com.daydayup.myjddemo.model.CartBean;
import com.daydayup.myjddemo.model.MyModel;
import com.daydayup.myjddemo.model.MyModelCallBack;
import com.daydayup.myjddemo.view.view.iview.MyViewCallBack;

/**
 * Created by 因为有个你i on 2018/5/25.
 */

public class MyPresenter {
    MyModel myModel;
    MyViewCallBack myViewCallBack;
    public MyPresenter(MyViewCallBack myViewCallBack) {
        this.myViewCallBack = myViewCallBack;
        this.myModel = new MyModel();
    }


    public void select() {
        myModel.select(new MyModelCallBack() {
            @Override
            public void success(CartBean cartBean) {
                myViewCallBack.success(cartBean);
            }

            @Override
            public void failure() {
                myViewCallBack.failure();
            }
        });
    }

    public void detach() {
        this.myViewCallBack = null;
    }
}
