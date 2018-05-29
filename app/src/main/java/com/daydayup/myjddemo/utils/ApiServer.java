package com.daydayup.myjddemo.utils;

import com.daydayup.myjddemo.model.AddCartBean;
import com.daydayup.myjddemo.model.FenleiLeftBean;
import com.daydayup.myjddemo.model.FenleiLiebiaoBean;
import com.daydayup.myjddemo.model.FenleiRightBean;
import com.daydayup.myjddemo.model.Jiugongge_bean;
import com.daydayup.myjddemo.model.LoginBean;
import com.daydayup.myjddemo.model.RegBean;
import com.daydayup.myjddemo.model.Shouye_bean;
import com.daydayup.myjddemo.model.XQBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by 因为有个你i on 2018/5/18.
 */

public interface ApiServer {
    //注册
    @POST
    Observable<RegBean> getZhu(@Url String url, @QueryMap Map<String,String> map);
    //登录
    @POST
    Observable<LoginBean> getLog(@Url String url, @QueryMap Map<String,String> map);
    //首页
    @GET("ad/getAd")
    Observable<Shouye_bean> getUser();

    @GET("product/getCatagory")
    Observable<Jiugongge_bean> getjiugongge();
    //商品列表
//    @GET(Api.PRODUCTDETAIL)
//    Observable<PriceAndCountEvent> getProductDetail(@Query("pid") String pid);


    @GET("product/getCatagory")
    Observable<FenleiLeftBean> getdatas();
    //http://120.27.23.105/product/getProductCatagory
    @GET("product/getProductCatagory")
    Observable<FenleiRightBean> getRightData(@Query("cid") int cid);
    @POST
    Observable<FenleiLiebiaoBean> getliebiaoData(@Url String url, @QueryMap Map<String,Integer> map);
    //商品详情
    @FormUrlEncoded
    @POST("product/getProductDetail")
    Observable<XQBean> getGoodsInfo(@Field("pid") String pid, @Field("source") String source);

    //查询购物车,
    //http://120.27.23.105/product/getCarts?source=android&uid=1650
    @GET
    Observable<String> select(@Url String url, @QueryMap Map<String,String> map);

    //删除购物车
//    http://120.27.23.105/product/deleteCart?uid=1650&pid=1
    @GET
    Observable<String> delete(@Url String url, @QueryMap Map<String,String> map);

    //更新购物车
    // "token": "2FC3EF31EA25696D2715A971ADE38DE1",
//https://www.zhaoapi.cn/product/updateCarts?uid=1650&sellerid=1&pid=1&selected=0&num=10&token=2FC3EF31EA25696D2715A971ADE38DE1
    @GET
    Observable<String> update(@Url String url,@QueryMap Map<String,String> map);



    Call<AddCartBean> addCart(Map<String, String> map);
}
