package com.daydayup.myjddemo.model;

import com.daydayup.myjddemo.view.view.iview.APIFactory;
import com.daydayup.myjddemo.view.view.iview.AbstractObserver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 因为有个你i on 2018/5/25.
 */

public class MyModel {
    public void select(final MyModelCallBack myModelCallBack) {

        final Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","1650");

        //调用apifactory里面的方法
        APIFactory.getInstance().select("/product/getCarts", map, new AbstractObserver<CartBean>() {
            @Override
            public void onSuccess(CartBean cartBean) {
                myModelCallBack.success(cartBean);
            }

            @Override
            public void onFailure() {
                myModelCallBack.failure();
            }
        });
    }
}
