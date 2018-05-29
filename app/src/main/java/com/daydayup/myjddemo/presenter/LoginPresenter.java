package com.daydayup.myjddemo.presenter;

import com.daydayup.myjddemo.view.view.iview.IView;

import com.daydayup.myjddemo.model.LoginBean;
import com.daydayup.myjddemo.model.LoginModel;

/**
 * Created by 因为有个你i on 2018/5/17.
 */

public class LoginPresenter{

    private final IView iview;
    private final LoginModel mymodel;

    public LoginPresenter(IView iview) {
        this.iview = iview;
        this.mymodel=new LoginModel();
    }
    public void start(String url,String mobile,String password){
        mymodel.MymodelOver(url, mobile, password);
        mymodel.setOnchanges(new LoginModel.ovderModel() {
            @Override
            public void setOnchang(LoginBean s) {
                if (s.getCode().equals("1")){
                    iview.overfairl();
                }else{
                    iview.overAdapter(s.getData().getMobile());
                }

            }
        });
    }
}
