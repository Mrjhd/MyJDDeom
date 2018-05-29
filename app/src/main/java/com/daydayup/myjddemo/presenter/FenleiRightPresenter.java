package com.daydayup.myjddemo.presenter;

import com.daydayup.myjddemo.model.FenleiLeftBean;
import com.daydayup.myjddemo.model.FenleiRightBean;
import com.daydayup.myjddemo.model.FenleiRightIModel;
import com.daydayup.myjddemo.model.FenleiRightModel;
import com.daydayup.myjddemo.model.OnRequestListener;
import com.daydayup.myjddemo.view.view.iview.FenleiRightIView;

import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public class FenleiRightPresenter implements FenleiRightIPresenter {
    private FenleiRightIView fenleiRightIView;
    private FenleiRightIModel fenleiRightIModel;

    public FenleiRightPresenter(FenleiRightIView fenleiRightIView) {
        this.fenleiRightIView = fenleiRightIView;
        fenleiRightIModel = new FenleiRightModel();
    }
    @Override
    public void loadRightList(String url, int cid) {
        fenleiRightIModel.RequestRightData(url, cid, new OnRequestListener() {
            @Override
            public void OnSuccess(List<FenleiLeftBean.DataBean> list) {

            }



            @Override
            public void OnRightSuccess(List<FenleiRightBean.DataBean> list) {
                fenleiRightIView.showRight(list);
            }

            @Override
            public void OnError(String e) {

            }
        });
    }
}
