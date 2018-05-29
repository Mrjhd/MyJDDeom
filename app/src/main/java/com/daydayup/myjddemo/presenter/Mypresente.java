package com.daydayup.myjddemo.presenter;

import android.content.Context;

import com.daydayup.myjddemo.model.Getjiugongge;
import com.daydayup.myjddemo.model.Getjson;
import com.daydayup.myjddemo.model.Imode;
import com.daydayup.myjddemo.model.Jiugongge_bean;
import com.daydayup.myjddemo.model.Shouye_bean;
import com.daydayup.myjddemo.view.view.Iview;

import java.util.List;

/**
 * Created by 因为有个你i on 2018/5/18.
 */

public class Mypresente implements Ipressenter {
    @Override
    public void getmv(final Context context, final Iview iview, Imode imode) {
        imode.getnetjson(new Getjson() {
            @Override
            public void getnetjson(Shouye_bean shouye_bean) {
                iview.setadapter(context,shouye_bean);
            }
        });
    }


    @Override
    public void getmv1(final Iview iview, final Imode imode) {
        imode.getjiugonggejson(new Getjiugongge() {
            @Override
            public void getJiugongge(List<Jiugongge_bean.DataBean> data) {
                iview.setadapter1(data);
            }
        });
    }
}
