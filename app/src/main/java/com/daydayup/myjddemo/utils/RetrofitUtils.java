package com.daydayup.myjddemo.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 因为有个你i on 2018/5/25.
 */

public class RetrofitUtils {
    private static ApiServer service = null;

    //http://api.tianapi.com/nba/?key=71e58b5b2f930eaf1f937407acde08fe&num=10
    //单例模式
    public static ApiServer getInstance(){
        if(service==null){
            synchronized (RetrofitUtils.class){
                if(service==null){
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://120.27.23.105")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(OkHttpUtils.getInstance())
                            .build();

                    service = retrofit.create(ApiServer.class);
                }
            }
        }
        return service;
    }
}
