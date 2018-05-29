package com.daydayup.myjddemo.view.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daydayup.myjddemo.R;
import com.daydayup.myjddemo.presenter.LoginPresenter;
import com.daydayup.myjddemo.utils.Api;
import com.daydayup.myjddemo.utils.SharedPreferencesUtils;
import com.daydayup.myjddemo.view.view.iview.IView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements IView {


    @BindView(R.id.login_title_relative)
    RelativeLayout loginTitleRelative;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.text_regist)
    TextView textRegist;
    @BindView(R.id.login_by_wechat)
    ImageView loginByWechat;
    @BindView(R.id.login_by_qq)
    ImageView loginByQq;
    @BindView(R.id.login_back)
    ImageView loginBack;
    private LoginPresenter presenter;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);


    }


    @OnClick({R.id.login_btn, R.id.text_regist, R.id.login_by_wechat, R.id.login_by_qq,R.id.login_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                final String string = editPhone.getText().toString();
                 String string1 = editPwd.getText().toString();
                if (string.equals("") || string1.equals("")) {
                    Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                }

                loginPresenter.start(Api.Login, string, string1);

                break;
            case R.id.text_regist:
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
                break;
            case R.id.login_by_wechat:

                break;
            case R.id.login_by_qq:
                UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.login_back:
                finish();
                break;
        }
    }

    @Override
    public void overAdapter(String logs) {

        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
        SharedPreferencesUtils.setParam(LoginActivity.this,"bolean",true);
        SharedPreferencesUtils.setParam(LoginActivity.this,"name",logs);
        finish();
    }

    @Override
    public void overfairl() {
        Toast.makeText(LoginActivity.this, "天呐，用户不存在,请先注册", Toast.LENGTH_SHORT).show();
    }

    private static final String TAG = "MainActivity--------";
    //登录监听
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();

            //遍历map集合
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                String value = data.get(key);
                Log.d(TAG, "信息" + platform + "---" + action + "---" + key + "----" + value);

            }

            finish();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }

    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }
}
