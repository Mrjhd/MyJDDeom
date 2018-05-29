package com.daydayup.myjddemo.view.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daydayup.myjddemo.R;
import com.daydayup.myjddemo.presenter.Zhupersenter;
import com.daydayup.myjddemo.utils.Api;
import com.daydayup.myjddemo.view.view.iview.ZView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity implements ZView {

    @BindView(R.id.cha_iamge)
    ImageView chaIamge;
    @BindView(R.id.login_title_relative)
    RelativeLayout loginTitleRelative;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.reg_btn)
    Button regBtn;
    private Zhupersenter zhucepresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        zhucepresenter = new Zhupersenter(this);
    }

    @Override
    public void zhusucess() {

        Toast.makeText(RegActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void zhufarile() {

        Toast.makeText(RegActivity.this,"天呐用户已经存在",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.reg_btn)
    public void onViewClicked() {
        final String string = editPhone.getText().toString();
        final String string1 = editPwd.getText().toString();
        if(string.equals("")||string1.equals("")){
            Toast.makeText(RegActivity.this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
        }
        zhucepresenter.zhustart(Api.Zhuce,string,string1);
    }
}
