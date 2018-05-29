package com.daydayup.myjddemo.utils;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public interface OnNetListener<T> {
    public void onSuccess(T t);

    public void onFailure(Exception e);
}
