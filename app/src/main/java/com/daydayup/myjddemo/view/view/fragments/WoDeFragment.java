package com.daydayup.myjddemo.view.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daydayup.myjddemo.R;
import com.daydayup.myjddemo.utils.SharedPreferencesUtils;
import com.daydayup.myjddemo.view.view.activities.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class WoDeFragment extends BaseFragment {

    @BindView(R.id.wd_touxiang)
    ImageView wdTouxiang;
    @BindView(R.id.wd_login)
    TextView wdLogin;
    Unbinder unbinder;

    @BindView(R.id.login_back_pic)
    LinearLayout loginBackPic;
    private View view;


    //页面
    @Override
    View createView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.layout_wd, null);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String name = (String) SharedPreferencesUtils.getParam(getContext(), "name", "");
        String iconUrl = (String) SharedPreferencesUtils.getParam(getContext(), "iconUrl", "");
        String uid = (String) SharedPreferencesUtils.getParam(getContext(), "uid", "");
        if (!TextUtils.isEmpty(uid)) {
            //登录过
            loginBackPic.setBackgroundResource(R.drawable.normal_regbg);
        } else {
            //未登录
            loginBackPic.setBackgroundResource(R.drawable.reg_bg);
        }
        if (!TextUtils.isEmpty(iconUrl)) {
            Glide.with(getContext()).load(iconUrl).into(wdTouxiang);
        }
        if (!TextUtils.isEmpty(name)) {
            wdLogin.setText(name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.wd_touxiang, R.id.wd_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wd_touxiang:

                break;
            case R.id.wd_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;


        }
    }


}
