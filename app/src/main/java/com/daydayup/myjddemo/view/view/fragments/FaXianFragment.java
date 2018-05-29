package com.daydayup.myjddemo.view.view.fragments;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.daydayup.myjddemo.R;


public class FaXianFragment extends BaseFragment {

    private WebView Web;

    private View view;
    String url="https://h5.m.jd.com/active/faxian/list/article-list.html";


    //页面
    @Override
    View createView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.layout_fx,null);
        initView(view);

        return view;
    }
    private View initView(@NonNull final View view) {
        Web = (WebView) view.findViewById(R.id.web);
        WebSettings webSettings = Web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        Web.loadUrl(url);
        return view;

    }

}
