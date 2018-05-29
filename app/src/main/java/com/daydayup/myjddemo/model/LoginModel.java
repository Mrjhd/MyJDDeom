package com.daydayup.myjddemo.model;


import android.util.Log;

import com.daydayup.myjddemo.utils.ApiServer;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 因为有个你i on 2018/5/17.
 */

public class LoginModel implements IModel{


    @Override
    public void MymodelOver(String url, String mobile, String password) {
        Map<String,String> map=new HashMap<>();
        Log.d("mylog", "MymodelOver: 我规范和健康了" + url + mobile +"---"+password);
        map.put("mobile",mobile);
        map.put("password",password);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        final Observable<LoginBean> log = apiServer.getLog("login",map);
        log.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean log) {
                        over.setOnchang(log);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public  interface  ovderModel{
        void setOnchang(LoginBean log);
    }
    private ovderModel  over;
    public void setOnchanges(ovderModel  over){
        this.over=over;
    }
    }

