package com.daydayup.myjddemo.utils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 因为有个你i on 2018/5/24.
 */

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.method().equals("GET")){
            String url = request.url().url().toString();
            url += "&source=android";
            Request newRequest = request.newBuilder().url(url).build();
            return chain.proceed(newRequest);
        }else {
            FormBody formBody = (FormBody) request.body();
            FormBody.Builder builder = new FormBody.Builder();
            for (int i = 0; i <formBody.size(); i++){
                builder.add(formBody.name(i), formBody.value(i));
            }
            builder.add("source", "android");
            FormBody newFormBody = builder.build();
            Request newRequest = request.newBuilder().url(request.url().url().toString()).post(newFormBody).build();
            return chain.proceed(newRequest);
        }
    }
}
