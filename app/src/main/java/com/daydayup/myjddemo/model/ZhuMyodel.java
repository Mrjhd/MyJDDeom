package com.daydayup.myjddemo.model;

import com.daydayup.myjddemo.utils.ApiServer;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 因为有个你i on 2018/5/18.
 */

public class ZhuMyodel implements Zhumodel {
    @Override
    public void zhuLog(String url, String mobile, String password) {
        HashMap<String ,String> map=new HashMap<>();
        map.put("mobile",mobile);
        map.put("password",password);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<RegBean> reg = apiServer.getZhu("reg", map);
        reg.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegBean zhu) {
                        sets.zhuOver(zhu);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface setOverzhu{
        void zhuOver(RegBean zhu);
    }
    private setOverzhu  sets;
    public void setChangce(setOverzhu  sets){
        this.sets=sets;
    }
    }
