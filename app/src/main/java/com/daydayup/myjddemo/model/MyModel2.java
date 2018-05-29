package com.daydayup.myjddemo.model;

import com.daydayup.myjddemo.view.view.iview.APIFactory;
import com.daydayup.myjddemo.view.view.iview.AbstractObserver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 因为有个你i on 2018/5/25.
 */

public class MyModel2 {
    public void delete(String pid,final MyModelCallBack2 myModelCallBack2) {

        final Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("pid",pid);
        map.put("uid","1650");

        //调用apifactory里面的方法
        APIFactory.getInstance().delete("/product/deleteCart", map, new AbstractObserver<DeleteBean>() {
            @Override
            public void onSuccess(DeleteBean deleteBean) {
                myModelCallBack2.success(deleteBean);
            }

            @Override
            public void onFailure() {
                myModelCallBack2.failure();
            }
        });
    }
}
