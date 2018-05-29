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

public class FenleiRightModel implements FenleiRightIModel {
    @Override
    public void RequestRightData(String url, int cid, final OnRequestListener onRequestListener) {
        //retrofit网络请求
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiServer apiService = retrofit.create(ApiServer.class);
        Observable<FenleiRightBean> rightData = apiService.getRightData(cid);
        rightData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FenleiRightBean>() {


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
                    public void onNext(FenleiRightBean fenleiRightBean) {
                        List<FenleiRightBean.DataBean> data = fenleiRightBean.getData();
                        onRequestListener.OnRightSuccess(data);
                    }
                });

    }
}
