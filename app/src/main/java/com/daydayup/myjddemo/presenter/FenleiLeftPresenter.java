package com.daydayup.myjddemo.presenter;

import com.daydayup.myjddemo.model.FenleiLeftBean;
import com.daydayup.myjddemo.model.FenleiLeftIModel;
import com.daydayup.myjddemo.model.FenleiLeftModel;
import com.daydayup.myjddemo.model.FenleiRightBean;
import com.daydayup.myjddemo.model.OnRequestListener;
import com.daydayup.myjddemo.view.view.iview.FenleiLeftIView;

import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public class FenleiLeftPresenter implements FenleileftIPresenter{

        private FenleiLeftIView fenleiLeftIView;
        private FenleiLeftIModel fenleiLeftIModel;

    public FenleiLeftPresenter(FenleiLeftIView fenleiLeftIView) {
            this.fenleiLeftIView = fenleiLeftIView;
            fenleiLeftIModel = new FenleiLeftModel();
        }
        @Override
        public void loadList(String url) {
            fenleiLeftIModel.RequestData(url, new OnRequestListener() {
                @Override
                public void OnSuccess(List<FenleiLeftBean.DataBean> list) {
                    fenleiLeftIView.showList(list);
                }

                @Override
                public void OnRightSuccess(List<FenleiRightBean.DataBean> list) {

                }

                @Override
                public void OnError(String e) {
                    fenleiLeftIView.showError(e);
                }
            });

        }

    }

