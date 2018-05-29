package com.daydayup.myjddemo.model;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public interface LieBiaoIModel {
    void RequestRightData(String url,int pscid,int page,int sort,OnLieBiaoListener onLieBiaoListener);
}
