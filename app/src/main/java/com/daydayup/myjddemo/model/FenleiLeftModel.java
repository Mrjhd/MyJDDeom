package com.daydayup.myjddemo.model;

import com.daydayup.myjddemo.utils.ApiServer;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public class FenleiLeftModel implements FenleiLeftIModel {
    @Override
    public void RequestData(String url, final OnRequestListener onRequestListener) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build();
        ApiServer apiService = retrofit.create(ApiServer.class);
        Observable<FenleiLeftBean> getdatas = apiService.getdatas();
        getdatas.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FenleiLeftBean>() {


                    @Override
                    public void onError(Throwable e) {
                        onRequestListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FenleiLeftBean fenleiLeftBean) {
                        List<FenleiLeftBean.DataBean> data = fenleiLeftBean.getData();
                        onRequestListener.OnSuccess(data);
                    }
                });
    }

}

