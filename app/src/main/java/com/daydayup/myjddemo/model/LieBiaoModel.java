package com.daydayup.myjddemo.model;

import com.daydayup.myjddemo.utils.ApiServer;

import java.util.HashMap;
import java.util.List;
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
 * Created by 因为有个你i on 2018/5/23.
 */

public class LieBiaoModel implements LieBiaoIModel {
    @Override
    public void RequestRightData(String url, int pscid ,int page,int sort,final OnLieBiaoListener onLieBiaoListener) {
        Map<String,Integer> map=new HashMap<>();
        map.put("pscid",pscid);
        map.put("page",page);
        map.put("sort",sort);
        //retrofit网络请求
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiServer apiService = retrofit.create(ApiServer.class);
        Observable<FenleiLiebiaoBean> fenleiLiebiaoBeanObservable = apiService.getliebiaoData("product/getProducts",map);
        fenleiLiebiaoBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FenleiLiebiaoBean>() {


                    @Override
                    public void onError(Throwable e) {
                        onLieBiaoListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FenleiLiebiaoBean fenleiLiebiaoBean) {
                        List<FenleiLiebiaoBean.DataBean> data = fenleiLiebiaoBean.getData();
                        onLieBiaoListener.OnRightSuccess(data);
                    }
                });
    }
}
