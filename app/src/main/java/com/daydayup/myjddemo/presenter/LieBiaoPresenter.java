package com.daydayup.myjddemo.presenter;

import com.daydayup.myjddemo.model.FenleiLiebiaoBean;
import com.daydayup.myjddemo.model.LieBiaoIModel;
import com.daydayup.myjddemo.model.LieBiaoModel;
import com.daydayup.myjddemo.model.OnLieBiaoListener;
import com.daydayup.myjddemo.view.view.iview.LieBiaoIView;

import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/23.
 */

public class LieBiaoPresenter implements LieBiaoIPresenter{
    private LieBiaoIView lieBiaoIView;
    private LieBiaoIModel lieBiaoIModel;

    public LieBiaoPresenter(LieBiaoIView lieBiaoIView) {
        this.lieBiaoIView = lieBiaoIView;
        lieBiaoIModel = new LieBiaoModel();
    }
    @Override
    public void loadRightList(String url, int pscid,int page,int sort) {
        lieBiaoIModel.RequestRightData(url, pscid,page,sort, new OnLieBiaoListener() {
            @Override
            public void OnRightSuccess(List<FenleiLiebiaoBean.DataBean> list) {
                lieBiaoIView.showList(list);
            }

            @Override
            public void OnError(String e) {
                lieBiaoIView.showError(e);
            }
        });
    }
}
