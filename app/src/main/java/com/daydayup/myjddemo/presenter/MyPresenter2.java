package com.daydayup.myjddemo.presenter;

import com.daydayup.myjddemo.model.DeleteBean;
import com.daydayup.myjddemo.model.MyModel2;
import com.daydayup.myjddemo.model.MyModelCallBack2;
import com.daydayup.myjddemo.view.view.iview.MyViewCallBack2;


/**
 * Created by 因为有个你i on 2018/5/25.
 */

public class MyPresenter2 {
    MyModel2 myModel2;
    MyViewCallBack2 myViewCallBack2;
    public MyPresenter2(MyViewCallBack2 myViewCallBack2) {
        this.myViewCallBack2 = myViewCallBack2;
        this.myModel2 = new MyModel2();
    }


    public void delete(String pid) {
        myModel2.delete(pid,new MyModelCallBack2() {
            @Override
            public void success(DeleteBean deleteBean) {
                myViewCallBack2.success(deleteBean);
            }

            @Override
            public void failure() {
                myViewCallBack2.failure();
            }
        });
    }

    public void detach() {
        this.myViewCallBack2 = null;
    }
}
