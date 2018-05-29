package com.daydayup.myjddemo.model;

import com.daydayup.myjddemo.utils.ApiServer;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 因为有个你i on 2018/5/28.
 */

public class AddCartModel {
    public void getData(String pid, final AddCartModelCallBack addCartModelCallBack) {
        // https://www.zhaoapi.cn/product/addCart?source=android&uid=1650&pid=57
        //"uid": 1650,
        // "token": "2FC3EF31EA25696D2715A971ADE38DE1",
        //"pid":57
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServer service = retrofit.create(ApiServer.class);

        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","1650");
        map.put("token","2FC3EF31EA25696D2715A971ADE38DE1");
        map.put("pid",pid);

        service.addCart(map).enqueue(new Callback<AddCartBean>() {
            @Override
            public void onResponse(Call<AddCartBean> call, Response<AddCartBean> response) {
                AddCartBean addCartBean = response.body();
                addCartModelCallBack.success(addCartBean);
            }

            @Override
            public void onFailure(Call<AddCartBean> call, Throwable t) {
                addCartModelCallBack.failure();
            }
        });
    }
}
